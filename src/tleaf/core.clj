(ns tleaf.core
  (:gen-class)
  (:import (org.thymeleaf TemplateEngine)
           (org.thymeleaf.context Context)
           (org.thymeleaf.resourceresolver FileResourceResolver)
           (org.thymeleaf.templateresolver TemplateResolver)))

(defn -main
  [& args]
  (let [tr (TemplateResolver.)]
    (.setResourceResolver tr (FileResourceResolver.))
    (let [engine (TemplateEngine.)]
      (.setTemplateResolver engine tr)
      (let [context (Context.)]
        (.setVariable context "message" "Hello, Thymeleaf!")
        (print (.process engine "src/tleaf/index.html" context))))))


