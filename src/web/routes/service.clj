(ns web.routes.service
  (:require [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]

            ;; Own code imports
            [core.time :refer [get-server-uptime]])

  (:import (dto.time Uptime)))

(defroutes service-routes
  (GET "/info" []
    "Returns server uptime and status information."

    (let [^Uptime uptime (get-server-uptime)]
      (ok (str "Web server started successfully. Work time: "
               (:hours uptime) "h "
               (:minutes uptime) "min "
               (:seconds uptime) "sec "
               (:millis uptime) "millis")))))
