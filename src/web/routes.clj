(ns web.routes
  (:require [cheshire.core :refer [generate-string]]
            [compojure.core :refer [defroutes GET ANY]]
            [ring.util.http-response :refer [ok not-found]]

            ;; Own code imports
            [memory :refer [show-links]]
            [time :refer [get-server-uptime]]
            [constants :refer [BASIC-LINKS-PULL-SIZE]])
  (:import (time Uptime)))

(defroutes app-routes
  (GET "/info" []
    (let [^Uptime uptime (get-server-uptime)]
      (ok (str "Web server started successfully. Work time: "
               (:hours uptime) "h "
               (:minutes uptime) "min "
               (:seconds uptime) "sec "
               (:millis uptime) "millis"))))

  (GET "/show/:length" request
    (let [length (-> request :params :length parse-long)]
      (cond
        ;; if length = nil -> use default value
        (nil? length) (-> (show-links BASIC-LINKS-PULL-SIZE)
                          generate-string
                          ok)

        ;; if length = 0 -> show all links
        (zero? length) (-> (show-links)
                           generate-string
                           ok)

        ;; show last <length> links
        :else (-> (show-links length)
                  generate-string
                  ok))))

  (ANY "*" [] (not-found "Not Found")))
