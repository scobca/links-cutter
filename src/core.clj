(ns core
  (:require [domain.link :as link]))

(println (link/create-link "https://example.com" "short-code"))
(println (link/create-link "https://example.com"))
