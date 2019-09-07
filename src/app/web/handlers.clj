(ns app.web.handlers
  (:require
    [app.domain.arithmetic]
    [app.domain.message]
    [integrant.core :as ig]
    [reitit.coercion.spec]
    [reitit.ring :as ring]
    [reitit.ring.coercion :as coercion]
    [reitit.ring.middleware.parameters :as parameters]))


(defmethod ig/init-key ::now
  [_ {:keys [prefix now]}]
  (fn [_]
    {:status 200
     :body (app.domain.message/message prefix now)
     :headers {"Content-Type" "text/plain"}}))


(defmethod ig/init-key ::ping
  [_ {:keys [now]}]
  (fn [context]
    {:status 200
     :body (app.domain.arithmetic/add (get-in context [:params "feedback"]) now)
     :headers {"Content-Type" "text/plain"}}))


(defmethod ig/init-key ::add
  [_ {:keys [now]}]
  (fn [context]
    (let [{{{:keys [x y]} :query} :parameters} context]
      {:status 200
       :body (app.domain.arithmetic/add x y now)
       :headers {"Content-Type" "text/plain"}})))


(defmethod ig/prep-key ::ring-handler
  [_ config]
  (merge config
         {:routes
          ["/api"
           [["/now" {:get (ig/ref ::now)}]
            ["/ping" {:get (ig/ref ::ping)}]
            ["/add" {:get {:parameters {:query {:x int? :y int?}}
                           :handler (ig/ref ::add)}}]]]

          :options
          {:data {:coercion   reitit.coercion.spec/coercion
                  :middleware [parameters/parameters-middleware
                               coercion/coerce-request-middleware]}}}))

(defmethod ig/init-key ::ring-handler
  [_ {:keys [routes options]}]
  (ring/ring-handler (ring/router routes options)))
