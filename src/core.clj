(ns core
  (:require
   [domain.link :as link]
   [memory :refer [show-links]]))

(println (link/create-link-record! "https://example.com" "short-code"))
(println (link/create-link-record! "https://example.com"))

(let [links (show-links)]
  (doseq [[code url] links]
    (println "Code:" code "—> URL:" url)))
