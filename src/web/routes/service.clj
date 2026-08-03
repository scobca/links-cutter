(ns web.routes.service
  (:require [compojure.core :refer [defroutes context GET]]
            [ring.util.http-response :refer [ok]]

            ;; Own code imports
            [core.time :refer [get-server-uptime]])

  (:import (dto.time Uptime)))

(defroutes service-routes
  (context "/api/v1" []
    (GET "/info" []
      "Returns server uptime and status information."

      (let [^Uptime uptime (get-server-uptime)]
        (ok {:message (str "Web server started successfully. Work time: "
                           (:hours uptime) "h "
                           (:minutes uptime) "min "
                           (:seconds uptime) "sec "
                           (:millis uptime) "millis")})))))
