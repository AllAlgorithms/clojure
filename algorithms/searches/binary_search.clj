(ns searches.binary-search)

(defn binary-search 
  "Takes a sorted sequence s and returns an index of x in s.
   Returns nil if x is not in s."
  [s x]
  (let [s (if (vector? s) s (vec s))]
    (loop [low 0
           high (count s)]
      (when (< low high)
        (let [mid (+ low (quot (- high low) 2))
              mid-val (get s mid)]
          (cond
            (< x mid-val) (recur low mid)
            (= x mid-val) mid
            (> x mid-val) (recur (inc mid) high)))))))
