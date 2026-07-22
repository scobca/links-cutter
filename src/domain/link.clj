(ns domain.link
  (:require [domain.generator :as gen]))

(defn valid-url?
  [url]
  (and (string? url)
       (not-empty url)))

(defn create-link
  ([url code]
   (when (valid-url? url)
     {:url  url
      :code code}))

  ([url]
   (when (valid-url? url)
     {:url  url
      :code (gen/generate-code)})))