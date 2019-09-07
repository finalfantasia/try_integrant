(ns app.web.handlers
  (:require
    [app.domain.arithmetic]
    [app.domain.message]
    [reitit.coercion.spec]
    [reitit.ring :as ring]
    [reitit.ring.coercion :as coercion]
    [reitit.ring.middleware.parameters :as parameters]))


(defn now-handler
  [prefix now]
  (fn [_]
    {:status 200
     :body (app.domain.message/message prefix now)
     :headers {"Content-Type" "text/plain"}}))

(defn ping-handler
  [now]
  (fn [context]
    {:status 200
     :body (app.domain.message/message (get-in context [:params "feedback"]) now)
     :headers {"Content-Type" "text/plain"}}))

(defn add-handler
  [now]
  (fn [context]
    (let [{{{:keys [x y]} :query} :parameters} context]
      {:status 200
       :body (app.domain.arithmetic/add x y now)
       :headers {"Content-Type" "text/plain"}})))

(defn make-routes-options
  [{:keys [now-handler ping-handler add-handler]}]
  {:routes
   ["/api"
    [["/now" {:get now-handler}]
     ["/ping" {:get ping-handler}]
     ["/add" {:get {:parameters {:query {:x int? :y int?}}
                    :handler add-handler}}]]]

   :options
   {:data {:coercion   reitit.coercion.spec/coercion
           :middleware [parameters/parameters-middleware
                        coercion/coerce-request-middleware]}}})
