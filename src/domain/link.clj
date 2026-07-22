(ns domain.link)

(defn valid-url?
  [url]
  (and (string? url)
       (not-empty url)))

(defn create-link
  [url code]
  (when (valid-url? url)
    {:url  url
     :code code}))