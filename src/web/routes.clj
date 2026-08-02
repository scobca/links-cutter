(ns web.routes
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [time :refer [get-server-uptime]])
  (:import (time Uptime)))

(def app-routes
  (GET "/info" request
    (let [^Uptime uptime (get-server-uptime)]
      (ok (str "Web server started successfully. Work time: " (:hours uptime) "h " (:minutes uptime) "min " (:seconds uptime) "sec " (:millis uptime) "millis")))))
