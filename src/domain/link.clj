(ns domain.link
  (:require
   [domain.generator :refer [generate-code]]
   [memory :refer [code-occupied? save-link-record!]]))

(defn valid-url?
  [url]
  (and (string? url)
       (not-empty url)))

(defn create-link-record!
  ([url code]
   (cond
     (not (valid-url? url))
     {:error "Invalid URL"}

     (code-occupied? code)
     (recur url (generate-code))

     :else
     (do
       (save-link-record! code url)
       {:url url
        :code code})))

  ([url]
   (create-link-record! url (generate-code))))