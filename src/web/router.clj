(ns web.router
  (:require [compojure.core :refer [defroutes ANY]]
            [ring.util.http-response :refer [not-found]]
            [web.routes.links :refer [links-routes]]
            [web.routes.service :refer [service-routes]]))

(defroutes app-routes
  links-routes
  service-routes
  (ANY "*" [] (not-found "Not Found")))