(ns math.quadratic-equation)

(defn calculate-delta
      [a b c]
      (- (* b b) (* 4 a c)))

(defn calculate-x [delta a b]
      (conj #{} (-> b - (+ (Math/sqrt delta)) (/ (* 2 a)))
                (-> b - (- (Math/sqrt delta)) (/ (* 2 a)))))

(defn quadratic-equation [a b c]
      (let [delta (calculate-delta a b c)]
           (when (>= delta 0)
             (calculate-x delta a b))))
