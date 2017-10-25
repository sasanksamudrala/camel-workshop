/*
 * Copyright 2017 Ivo Woltring <WebMaster@ivonet.nl>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.ordina.route.file;

import lombok.extern.slf4j.Slf4j;
import nl.ordina.context.CamelDemoContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * @author Ivo Woltring
 */
@Slf4j
@Component
public class FileCopyRoute extends RouteBuilder {

    private final CamelDemoContext context;

    @Autowired
    public FileCopyRoute(final CamelDemoContext context) {
        this.context = context;
    }

    @Override
    public void configure() throws Exception {
        final String projectBaseLocation = this.context.projectBaseLocation();
        final String name = this.getClass().getSimpleName();

// Now copy all xml files from the 'from' location to the <projectBaseLocation>/target/xml folder
// The from location is already provided in this exercise and the route is also given an id.
// Copy all text files to <projectBaseLocation>/target/txt folder
// also log what you are doing and try to use the 'simple' language
// hint: header("CamelFileName")

        //implement here...
        ;

// (Bonus) Question(s):
// - what would you need to change to make this copy into a move?
        from(format("file://%s/test-data/startingPoint/?noop=true", projectBaseLocation))
                .routeId(name)
                .choice()
                .when(header("CamelFileName").endsWith(".xml"))
                .log("Found file [$simple{header.CamelFileName}] and will copy to target/xml.")
                .to(format("file://%s/target/xml/", projectBaseLocation))
                .otherwise()
                .log("Found file [$simple{header.CamelFileName}] and copying it to target/txt")
                .to(format("file://%s/target/txt/", projectBaseLocation));
    }
}
