(defn get_fibonacci_value [number]
  
  (if (<= number 1) 1 (+ (get_fibonacci_value (- number 1)) (get_fibonacci_value (- number 2))) )
)
