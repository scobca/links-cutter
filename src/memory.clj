(ns memory)

(def links (atom {}))

(defn code-occupied?
  "Check for the presence of code into memory and return boolean.g"
  [code]
  (contains? @links code))

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
   (println "All links:")
   (doseq [[code url] @links]
     (println "  Code:" code "-> URL:" url)))

  ([size]
   (let [all-links @links
         total (count all-links)
         start (max 0 (- total size))
         links-to-show (->> all-links
                            (drop start)
                            (take size))]

     (println (str "Last " size " link(s) (total: " total "):"))
     (if (empty? links-to-show)
       (println "  No links to show")
       (doseq [[code url] links-to-show]
         (println "  Code:" code "-> URL:" url))))))