/**
 * This file Copyright (c) 2015-2019 Magnolia International
 * Ltd.  (http://www.magnolia-cms.com). All rights reserved.
 *
 *
 * This file is dual-licensed under both the Magnolia
 * Network Agreement and the GNU General Public License.
 * You may elect to use one or the other of these licenses.
 *
 * This file is distributed in the hope that it will be
 * useful, but AS-IS and WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE, TITLE, or NONINFRINGEMENT.
 * Redistribution, except as permitted by whichever of the GPL
 * or MNA you select, is prohibited.
 *
 * 1. For the GPL license (GPL), you can redistribute and/or
 * modify this file under the terms of the GNU General
 * Public License, Version 3, as published by the Free Software
 * Foundation.  You should have received a copy of the GNU
 * General Public License, Version 3 along with this program;
 * if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * 2. For the Magnolia Network Agreement (MNA), this file
 * and the accompanying materials are made available under the
 * terms of the MNA which accompanies this distribution, and
 * is available at http://www.magnolia-cms.com/mna.html
 *
 * Any modifications to this file must keep this entire header
 * intact.
 *
 */
package org.magnolia.telegram.bot.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * The Interface MgnlTegramBotRestService.
 */
public interface MgnlTegramBotRestService {

    /**
     * Gets the tours.
     *
     * @param authString the auth string
     * @param query      the query
     * @return the tours
     */
    @GET
    @Path("/tours/")
    @Produces(MediaType.APPLICATION_JSON)
    String getTours(@HeaderParam("authorization") String authString, @QueryParam("q") String query);

    /**
     * Gets the assets.
     *
     * @param authString the auth string
     * @param query      the query
     * @return the assets
     */
    @GET
    @Path("/assets/")
    @Produces(MediaType.APPLICATION_JSON)
    String getAssets(@HeaderParam("authorization") String authString, @QueryParam("q") String query);

    /**
     * Commands.
     *
     * @param authString  the auth string
     * @param catalogName the catalog name
     * @param commandName the command name
     * @param body        the body
     * @return the string
     */
    @POST
    @Path("/commands/v2/{catalogName}/{commandName}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String commands(@HeaderParam("authorization") String authString, @PathParam("catalogName") String catalogName,
            @PathParam("commandName") String commandName, String body);

}
