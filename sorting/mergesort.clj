(ns project2.core
  (:require [clojure.string :as str])
  (:require [clojure.java.io :as io])
  (:import (java.util.concurrent Executors)))

;;N. King 5/3/2017
;;Timing study on mergesort using concurrency
;;added comments to show conceptual understanding

(defn get-nums [file]    ;;defines function "get-nums" which takes a file parameter
  (map read-string       ;;returns lazy sequence of lines (numbers)
       (str/split-lines  ;;splits collection by line
         (slurp file)    ;;open file and read contents, returns string
         )
       )
  )


(defn split-2 [numSeq]     ;;split into 2
  (partition (/ (count numSeq) 2) numSeq))  ;count items in list, divide by two, partition the original list into lists
                                            ;containing (/ (count numSeq) 2) items each
(defn split-4 [numSeq]     ;;split into 4
  (partition (/ (count numSeq) 4) numSeq))

(defn split-8 [numSeq]     ;;split into 8
  (partition (/ (count numSeq) 8) numSeq))

(defn split-16 [numSeq]    ;;split into 16
  (partition (/ (count numSeq) 16) numSeq))

(defn split-32 [numSeq]    ;;split into 32
  (partition (/ (count numSeq) 32) numSeq))




(defn merge-seqs
  "Merges two sorted sequences into a single sorted sequence"
  ([left right]                                ;;if parameter is two items                     
   (merge-seqs (list left right)))             ;;merge them into a list
  ([[left right]]                              ;;if parameter is a list of two items
   (loop [l left, r right, result []]          ;;loop through both lists
     (let [lhead (first l), rhead (first r)]   ;;let the heads of the list be the first items
       (cond
         (nil? lhead)     (concat result r)    ;;if the lhead is null, append the rest of r to result
         (nil? rhead)     (concat result l)    ;;if the rhead is null, append the rest of l to result
         (<= lhead rhead) (recur (rest l) r (conj result lhead))     ;if the left head is less than the right head, continue loop with new parameters
         true             (recur l (rest r) (conj result rhead)))))));if right head is less than left head, continue loop with new parameters

(defn mergesort
  "Produces a sorted sequence from an input sequence.
  Works best with vectors (since it uses 'count' internally)."
  [xs]
  ((fn mergesort-counted [xs n]
     (if (<= n 1)                                     ;if only 1 item remains
       xs                                             ;return list
       (let [middle (bit-shift-right n 1)]            ;set middle for the list to split
         (merge-seqs (map mergesort-counted           ;call merge-seqs on two halves
                          (split-at middle xs)        ;two halves
                          [middle (- n middle)])))))  ;count of each half
    xs (count xs)))


(print "Single Thread mergesort: ") ;timing for simple mergesort --time results in milliseconds
(time (mergesort (get-nums "src/project2/numbers_txt.txt")))

(print "2 Threads mergesort: ") ;timing for 2 thread mergesort
(time (reduce merge-seqs (pmap mergesort (split-2 (get-nums "src/project2/numbers_txt.txt")))))
;;
;;pmap is similar to map, except mergesort is applied in parallel to all objects in collection
;;
(print "4 Threads mergesort: ") ;timing for 4 thread mergesort
(time (reduce merge-seqs (pmap mergesort (split-4 (get-nums "src/project2/numbers_txt.txt")))))

(print "8 Threads mergesort: ") ;timing for 8 thread mergesort
(time (reduce merge-seqs (pmap mergesort (split-8 (get-nums "src/project2/numbers_txt.txt")))))

(print "16 Threads mergesort: ") ;timing for 16 thread mergesort
(time (reduce merge-seqs (pmap mergesort (split-16 (get-nums "src/project2/numbers_txt.txt")))))

(print "32 Threads mergesort: ") ;timing for 32 thread mergesort
(time (reduce merge-seqs (pmap mergesort (split-32 (get-nums "src/project2/numbers_txt.txt")))))

;sources--
; merge sort algorithm from: https://gist.github.com/alco/2135276
; multithreading: https://gist.github.com/ChrisWphoto/c6f86f34cd7ade5055aec743ec990765
