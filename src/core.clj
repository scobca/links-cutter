(ns core
  (:require [core.config :refer [http-port]]
            [core.constants :refer [OPENAPI-JSON-DOCS-ROUTE OPENAPI-SWAGGER-DOCS-ROUTE]]
            [core.time :refer [set-start-time!]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-params wrap-json-response]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.swagger.swagger-ui :refer [wrap-swagger-ui]]
            [web.router :refer [app-routes]]))

(def app (-> app-routes
             (wrap-swagger-ui {:path OPENAPI-SWAGGER-DOCS-ROUTE
                               :swagger-docs OPENAPI-JSON-DOCS-ROUTE})
             wrap-params
             (wrap-json-params {:keywords? true})
             (wrap-json-response {:pretty true})))







(defn start-server []
  (run-jetty app {:port (http-port) :join? false})
  (set-start-time!))









(defn -main []
  (start-server)
  (println "Server started")
  @(promise))