(ns core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [web.routes :refer [app-routes]]
            [time :refer [set-start-time!]]))

(defn start-server []
  (run-jetty app-routes {:port 3000 :join? false})
  (set-start-time!))

(defn -main []
  (start-server)
  (println "Server started"))