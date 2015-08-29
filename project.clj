(defproject bago2k4/cljs-dynamic-deps "0.0.1"
  :description "Dynamic load javascript and style"
  :url "https://github.com/bago2k4/cljs-dynamic-deps"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [
    [org.clojure/clojure "1.7.0"] ; Lisp on the JVM http://clojure.org/documentation
    [org.clojure/clojurescript "1.7.107"] ; ClojureScript compiler https://github.com/clojure/clojurescript
    [prismatic/dommy "1.1.0"] ; DOM manipulation and event library https://github.com/Prismatic/dommy
  ]

  :profiles {
    :dev {
      :plugins [
        [lein-cljsbuild "1.0.5"]
        [com.cemerick/clojurescript.test "0.3.3"]]

      :cljsbuild {
        :test-commands {
          "unit" ["phantomjs" :runner "target/unit-test.js"]}
        :builds {
          :test {
            :source-paths ["src" "test"]
            :compiler {
              :output-to "target/unit-test.js"
              :optimizations :whitespace
              :pretty-print true}}}}}})

