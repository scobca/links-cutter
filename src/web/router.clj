(ns web.router
  (:require [compojure.core :refer [defroutes ANY]]
            [ring.util.http-response :refer [not-found]]
            [web.routes.links :refer [links-routes]]
            [web.routes.service :refer [service-routes]]
            [web.routes.openapi :refer [openapi-routes]]))

(defroutes app-routes
  openapi-routes
  service-routes
  links-routes
  (ANY "*" [] (not-found "Not Found")))