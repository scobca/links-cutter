(ns dto.link)

(defrecord LinkRecord [url code])

(defrecord CreateLinkRequest [^String url])