package com.feexon.jyose;

import com.feexon.jyose.Router;

import static com.feexon.jyose.handlers.Challeges.helloYose;
import static com.feexon.jyose.handlers.Challeges.ping;

/**
 * Created by L.x on 15-11-16.
 */
public class YoseRouter extends Router  {{
    draw("/", helloYose());
    draw("/ping", ping());
}}
