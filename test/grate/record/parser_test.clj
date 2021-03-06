(ns grate.record.parser-test
  (:require [clojure.test :refer :all]
            [grate.record.parser :refer :all]))

(deftest test-parse-record-bar
  (testing "Parse string of fields separated by a bar"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color "purple"
            :date-of-birth  "1970-10-10"}
           (parse " Crone |  Todd| M |purple  |   1970-10-10   ")))))

(deftest test-parse-record-csv
  (testing "Parse string of fields separated by a comma"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color "purple"
            :date-of-birth  "1970-10-10"}
           (parse " Crone,  Todd, M ,purple  ,   1970-10-10   ")))))

(deftest test-parse-record-whitespace
  (testing "Parse string of fields separated by a space"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color "purple"
            :date-of-birth  "1970-10-10"}
           (parse " Crone   Todd M purple     1970-10-10   ")))))

(deftest test-parse-partial-record
  (testing "Parse string of fields separated by a bar missing some columns"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color ""
            :date-of-birth  nil}
           (parse " Crone |  Todd| M |  ")))))

(deftest test-no-record
  (testing "Parse string of fields separated by a bar"
    (is (= {:last-name      ""
            :first-name     nil
            :gender         nil
            :favorite-color nil
            :date-of-birth  nil}
           (parse "")))))
