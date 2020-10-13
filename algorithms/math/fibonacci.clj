(ns math.fibonacci)

(defn fibonacci [n]
  (cond
    (<= n 0) 0
    (<= n 2) 1
    :else
    (loop [i 2, a 1N, b 1N]
      (if (= i n) b
        (recur (inc i) b (+ a b))))))
