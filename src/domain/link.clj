(ns domain.link
  (:require [domain.generator :refer [generate-code]]
            [core.memory :refer [code-occupied? save-link-record!]]
            [dto.link :refer [->LinkRecord]])
  (:import (dto.link LinkRecord)))

(defn valid-url?
  "Validate input URL, return boolean."
  [url]
  (if (and
       (string? url)
       (not-empty url))
    true
    false))

(defn ^LinkRecord create-link-record!
  "Create the record of link, and it's unique code into the in-memory database (Check memory.clj)."
  ([url code]
   (cond
     (not (valid-url? url))
     {:error "Invalid URL"}

     (code-occupied? code)
     (recur url (generate-code))

     :else
     (do
       (save-link-record! code url)
       (->LinkRecord url code))))

  ([url]
   (create-link-record! url (generate-code))))