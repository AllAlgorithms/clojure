(ns math.Least-Common-Multiple)

;; Write a function which calculates the least common multiple. Your function should accept a variable number of positive integers or ratios.

(defn LCM [& arg]
  ((fn kpk [& data]
     (let [data-first (nth data 0)
           data-new (nth data 1)
           normal-data (flatten data-new)
           x (vec normal-data)]
       (let [check (for [i (range (dec (count x)))]
                   (if (= (x i) (x (inc i)))
                     true
                     false))
             lowest (apply min x)]
         (if (some #(= false %) check)
           (kpk data-first
                (for [i (range (count x))]
                  (if (= (x i) lowest)
                    (+ (x i) (nth data-first i))
                    (x i))))
           (x 0)))))
   (vec arg) (vec arg)))

;; (== (LCM 2 3) 6)
;; (== (LCM 5 3 7) 105)
;; (== (LCM 1/3 2/5) 2)
;; (== (LCM 3/4 1/6) 3/2)
;; (== (LCM 7 5/7 2 3/5) 210)