(ns grate.record
  (:require [clojure.string :as str])
  (:require [clojure.data.json :as json]))

(defn create
  [values]
  (let [[last first gender color date] values]
    {:last-name      last
     :first-name     first
     :gender         gender
     :favorite-color color
     :date-of-birth  date}))

(defn split-n-trim
  [line delim]
  (create (map str/trim (str/split line delim))))

(defn bar?
  [line]
  (str/includes? line "|"))

(defn csv?
  [line]
  (str/includes? line ","))

(defn parse
  [line]
  (cond
    (bar? line) (split-n-trim line #"\|")
    (csv? line) (split-n-trim line #",")
    :else (split-n-trim (str/replace (str/trim line) #"\s+" ",") #",")))

(defn to-json
  [record]
  (json/write-str record))

(defn compare-on-last-name-desc
  [first second]
  (compare (:last-name second) (:last-name first)))

(defn compare-on-birth-date-asc
  [first second]
  (compare (:date-of-birth first) (:date-of-birth second)))

(defn compare-on-gender-asc-then-last-name-asc
  [first second]
  (compare (str (:gender first) (:last-name first)) (str (:gender second) (:last-name second))))

