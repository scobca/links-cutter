(ns web.routes
  (:require [compojure.core :refer [defroutes GET POST ANY]]
            [ring.util.http-response :refer [ok not-found bad-request]]

            ;; Own code imports
            [memory :refer [show-links]]
            [time :refer [get-server-uptime]]
            [domain.link :refer [create-link-record!]]
            [constants :refer [BASIC-LINKS-PULL-SIZE]]
            [dto.link-request :refer [map->CreateLinkRequest]])

  (:import (time Uptime)))

(defroutes app-routes
  (GET "/info" []
    "Returns server uptime and status information."

    (let [^Uptime uptime (get-server-uptime)]
      (ok (str "Web server started successfully. Work time: "
               (:hours uptime) "h "
               (:minutes uptime) "min "
               (:seconds uptime) "sec "
               (:millis uptime) "millis"))))

  (GET "/show/:length" request
    "Shows links. Optional :length parameter for limiting results."

    (let [length (-> request :params :length parse-long)]
      (cond
        ;; if length = nil -> use default value
        (nil? length) (-> (show-links BASIC-LINKS-PULL-SIZE) ok)

        ;; if length = 0 -> show all links
        (zero? length) (-> (show-links) ok)

        ;; show last <length> links
        :else (-> (show-links length) ok))))

  (POST "/" request
    "Creates a new shortened link. Expects JSON body with :url"

    (let [body (-> request :params (map->CreateLinkRequest))
          result (create-link-record! (:url body))]
      (if (:error result)
        (bad-request result)
        (ok result))))

  (ANY "*" []
    "Catch-all route for 404 Not Found"
    (not-found "Not Found")))
