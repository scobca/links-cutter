(ns memory)

(def links (atom {}))

(defn code-occupied?
  "Check for the presence of code into memory and return boolean.g"
  [code]
  (contains? @links code))

(defn find-link-record
  "Accepts link's short-code and return from memory struct of code and natural link."
  [code]
  (when-let [url (get @links code)]
    {:code code
     :url url}))

(defn save-link-record!
  "Accepts short-code and natural link (url), save it into memory."
  [code url]
  (swap! links assoc code url))

(defn remove-link-record!
  "Accepts link's short-code and remove link record from memory if exists."
  [code]
  (swap! links dissoc code))

(defn show-links
  "Show all links records (if args are empty) or show last N records (if user provide param size)."
  ([]
   @links)

  ([size]
   (let [all-links @links
         total (count all-links)
         start (max 0 (- total size))]
     (->> all-links
          (drop start)
          (take size)
          (into {})))))