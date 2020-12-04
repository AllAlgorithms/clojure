;; https://en.wikipedia.org/wiki/Shellsort
(ns sorting.shell-sort)

(def gaps [701 301 132 57 23 10 4 1])

(defn swap [coll i j]
  (assoc coll i (coll j) j (coll i)))

(defn shell-sort [coll]
  (let [result (atom coll)]
    (doseq [gap gaps
            i (range gap (count @result))
            j (reverse (range gap (inc i)))
            :let [k (- j gap)]
            :while (< (@result j) (@result k))]
      (reset! result (swap @result j k)))
    @result))

;; test
(shell-sort [8 3 1 5 2 1])
