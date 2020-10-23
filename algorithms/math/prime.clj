(ns math.prime)

(defn is-prime? [n]
  (loop [i 2]
    (cond
     (= i n) true
     (zero? (rem n i)) false
     :else (recur (+ i 1)))))
