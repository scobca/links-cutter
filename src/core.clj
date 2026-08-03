(ns core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-params wrap-json-response]]
            [ring.middleware.params :refer [wrap-params]]
            [web.routes :refer [app-routes]]
            [core.time :refer [set-start-time!]]
            [ring.middleware.params :refer [wrap-params]]))

(def app (-> app-routes
             wrap-params
             (wrap-json-params {:keywords? true})
             (wrap-json-response {:pretty true})))

(defn start-server []
  (run-jetty app {:port 3000 :join? false})
  (set-start-time!))

(defn -main []
  (start-server)
  (println "Server started")
  @(promise))