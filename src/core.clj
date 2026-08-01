(ns core
  (:require [time :refer [set-start-time! get-server-uptime]]))

(defn start-server []
  (set-start-time!))

(defn -main []
  (start-server)
  (println "Server started")

  (loop [i 0]
    (when (< i 120)
      (println (get-server-uptime))
      (Thread/sleep 1000)
      (recur (inc i)))))