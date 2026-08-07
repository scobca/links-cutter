(ns web.routes.openapi
  (:require [clojure.java.io :as io]
            [clj-yaml.core :as yaml]
            [compojure.core :refer [GET defroutes]]
            [muuntaja.core :as m]
            [core.constants :refer [OPENAPI-SCHEMA-FILE OPENAPI-JSON-DOCS-ROUTE]]
            [ring.util.http-response :refer [ok]]))

(defn openapi-json-handler [_]
  (try
    (let [yaml-content (slurp (io/resource OPENAPI-SCHEMA-FILE))
          parsed-yaml (yaml/parse-string yaml-content)
          json-content (m/encode "application/json" parsed-yaml)]
      (ok json-content))
    (catch Exception e
      {:status 500
       :body {:error "Failed to process OpenAPI schema"
              :message (.getMessage e)}})))

(defroutes openapi-routes
  (GET OPENAPI-JSON-DOCS-ROUTE [] openapi-json-handler))