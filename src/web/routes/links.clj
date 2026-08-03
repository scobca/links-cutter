(ns web.routes.links
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :refer [ok bad-request]]
            [core.memory :refer [show-links]]
            [domain.link :refer [create-link-record!]]
            [core.constants :refer [BASIC-LINKS-PULL-SIZE]]
            [dto.link-request :refer [map->CreateLinkRequest]]))

(defroutes links-routes

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
        (ok result)))))
