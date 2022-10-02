(ns math.Greatest-Common-Divisor)

;; Given two integers, write a function which returns the greatest common divisor.

(defn GCM [x y]
  (if (= y 0)
    x
    (GCM y (mod x y))))

;; (= (GCM 2 4) 2)
;; (= (GCM 10 5) 5)
;; (= (GCM 5 7) 1)
;; (= (GCM 1023 858) 33)