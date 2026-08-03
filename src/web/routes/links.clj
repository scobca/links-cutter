(ns web.routes.links
  (:require [compojure.core :refer [defroutes GET POST DELETE]]
            [ring.util.http-response :refer [not-found ok bad-request]]
            [core.memory :refer [show-links find-link-record remove-link-record!]]
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
        (ok result))))

  (DELETE "/:code" request
    "Delete shortened link by it code. Expects param :code"

    (let [code (-> request :params :code)
          record (find-link-record code)]

      (if record
        (do (remove-link-record! code)
            (ok {:message "Link deleted successful"}))
        (not-found {:message "Link with this code not found"})))))
