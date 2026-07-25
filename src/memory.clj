(ns memory)

(def links (atom {}))

(defn code-occupied? [code]
  (contains? @links code))

(defn save-link-record!
  [code url]
  (swap! links assoc code url))

(defn remove-link-record!
  [code]
  (swap! links dissoc code))

(defn show-links
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