(ns time
  (:import [java.time Duration Instant]))

(def start-time (atom nil))

(defn ^:private convert-java-duration
  "Convert java Instant time into custom Clojure time object"
  [^Duration duration]
  (let [hours (.toHours duration)
        minutes (mod (.toMinutes duration) 60)
        seconds (mod (.getSeconds duration) 60)
        millis (quot (.getNano duration) 1000000)]

    {:hours hours
     :minutes minutes
     :seconds seconds
     :millis millis}))

(defn get-server-uptime
  "Returns server uptime."
  []
  (if @start-time (->> (Instant/now)
                       (Duration/between @start-time)
                       (convert-java-duration))))

(defn set-start-time!
  "Updated server start time."
  ([] (set-start-time! (Instant/now)))
  ([^Instant time] (reset! start-time time)))