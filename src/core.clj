(ns core
  (:require [core.config :refer [http-port]]
            [core.time :refer [set-start-time!]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-params wrap-json-response]]
            [ring.middleware.params :refer [wrap-params]]
            [web.router :refer [app-routes]]))

(def app (-> app-routes
             wrap-params
             (wrap-json-params {:keywords? true})
             (wrap-json-response {:pretty true})))

(defn start-server []
  (run-jetty app {:port (http-port) :join? false})
  (set-start-time!))

(defn -main []
  (start-server)
  (println "Server started")
  @(promise))