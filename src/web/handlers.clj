(ns web.handlers
  (:require
    [domain.arithmetic]
    [domain.message]
    [expound.alpha :as expound]
    [muuntaja.core :as mc]
    [reitit.coercion.spec]
    [reitit.ring :as ring]
    [reitit.ring.coercion :as coercion]
    [reitit.ring.middleware.exception :as exception]
    [reitit.ring.middleware.muuntaja :as muuntaja]
    [reitit.ring.middleware.parameters :as parameters]))


(defn now-handler
  [prefix now]
  (fn [_]
    {:status 200
     :body (domain.message/message prefix now)
     :headers {"Content-Type" "text/plain"}}))

(defn ping-handler
  [now]
  (fn [context]
    {:status 200
     :body (domain.message/message (get-in context [:params "feedback"]) now)
     :headers {"Content-Type" "text/plain"}}))

(defn add-handler
  [now]
  (fn [context]
    (let [{{{:keys [x y]} :query} :parameters} context]
      {:status 200
       :body (domain.arithmetic/add x y now)
       :headers {"Content-Type" "text/plain"}})))

(defn coercion-error-handler [status]
  (let [printer (expound/custom-printer {:theme :figwheel-theme
                                         :print-specs? false})
        handler (exception/create-coercion-handler status)]
    (fn [exception request]
      (printer (-> exception (ex-data) (:problems)))
      (handler exception request))))

(defn make-routes-options
  [{:keys [now-handler ping-handler add-handler]}]
  {:routes
   ["/api"
    [["/now" {:get now-handler}]
     ["/ping" {:get ping-handler}]
     ["/add" {:get {:parameters {:query {:x int? :y int?}}
                    :responses {200 {:body string?}}
                    :handler add-handler}}]]]

   :options
   {:data {:coercion   reitit.coercion.spec/coercion
           :middleware [muuntaja/format-middleware
                        parameters/parameters-middleware
                        (exception/create-exception-middleware
                          (merge exception/default-handlers
                                 {:reitit.coercion/request-coercion (coercion-error-handler 400)
                                  :reitit.coercion/response-coercion (coercion-error-handler 500)}))
                        coercion/coerce-request-middleware
                        coercion/coerce-response-middleware]
           :muuntaja mc/instance}}})

