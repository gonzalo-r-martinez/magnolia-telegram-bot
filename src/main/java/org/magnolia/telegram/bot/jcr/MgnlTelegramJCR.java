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
package org.magnolia.telegram.bot.jcr;

import java.io.InputStream;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.magnolia.telegram.bot.beans.UserBean;
import org.magnolia.telegram.bot.beans.asset.Asset;
import org.magnolia.telegram.bot.beans.tour.Result;
import org.magnolia.telegram.bot.beans.tour.Tour;
import org.magnolia.telegram.bot.rest.MgnlTegramBotRestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import info.magnolia.context.LifeTimeJCRSessionUtil;
import info.magnolia.dam.jcr.AssetNodeTypes;
import info.magnolia.objectfactory.Components;
import info.magnolia.registry.RegistrationException;
import info.magnolia.repository.RepositoryConstants;
import info.magnolia.rest.client.registry.RestClientRegistry;
import info.magnolia.resteasy.client.RestEasyClient;

/**
 * The Class MgnlTelegramJCR. class for content access
 */
public class MgnlTelegramJCR {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(MgnlTelegramJCR.class);

    /** The Constant REST_CLIENT. */
    private static final String REST_CLIENT = "telegramBot";

    /** The Constant COMMAND_ACTIVATE. */
    private static final String COMMAND_ACTIVATE = "activate";

    /** The Constant COMMAND_RESULT_OK. */
    private static final String COMMAND_RESULT_OK = "{\"success\":true}";

    private static final String WORKSPACE_DAM = "dam";

    /**
     * Publish website.
     *
     * @param user     the user
     * @param pathPage the path page
     * @return true, if successful
     */
    public boolean publishWebsite(UserBean user, String pathPage) {
        log.debug("publishWebsite {}", pathPage);
        MgnlTegramBotRestService service = getService();
        String resultado = "";
        if (service != null) {
            try {
                resultado = service.commands(getAuthorizationToken(user), RepositoryConstants.WEBSITE, COMMAND_ACTIVATE,
                        "{" + "  \"repository\": \"" + RepositoryConstants.WEBSITE + "\"," + "  \"path\": \"" + pathPage
                                + "\"," + "  \"recursive\": \"false\"" + "}");
            } catch (Exception e) {
                log.error("Error publish page");
            }

        }
        log.debug("publishWebsite end {}", resultado);
        return resultado.equals(COMMAND_RESULT_OK);
    }

    /**
     * Gets the asset rest.
     *
     * @param user  the user
     * @param query the query
     * @return the asset rest
     */
    public List<org.magnolia.telegram.bot.beans.asset.Result> getAssetRest(UserBean user, String query) {
        log.debug("getAssetRest {}", query);
        MgnlTegramBotRestService service = getService();
        if (service != null) {
            String consulta = service.getAssets(getAuthorizationToken(user), query);
            Gson gson = new Gson();
            Asset assets = gson.fromJson(consulta, org.magnolia.telegram.bot.beans.asset.Asset.class);
            return assets.getResults();
        }
        log.debug("getAssetRest no result");
        return Collections.emptyList();
    }

    /**
     * Gets the tour rest.
     *
     * @param user  the user
     * @param query the query
     * @return the tour rest
     */
    public List<Result> getTourRest(UserBean user, String query) {
        log.debug("getTourRest {}", query);
        MgnlTegramBotRestService service = getService();
        if (service != null) {
            String consulta = service.getTours(getAuthorizationToken(user), query);
            Gson gson = new Gson();
            Tour tours = gson.fromJson(consulta, Tour.class);
            return tours.getResults();
        }
        log.debug("getTourRest no result");
        return Collections.emptyList();
    }

    /**
     * Gets the authorization token.
     *
     * @param user the user
     * @return the authorization token
     */
    private String getAuthorizationToken(UserBean user) {
        log.debug("getAuthorizationToken ");
        if (user != null) {
            String authString = user.getUsername() + ":" + user.getPassword();
            return "Basic " + Base64.getEncoder().encodeToString(authString.getBytes());
        } else {
            log.debug("getAuthorizationToken no user");
            return "";
        }
    }

    /**
     * Gets the service.
     *
     * @return the service
     */
    private MgnlTegramBotRestService getService() {
        log.debug("getService ");
        RestEasyClient client = null;
        try {
            client = (RestEasyClient) Components.getComponent(RestClientRegistry.class).getRestClient(REST_CLIENT);
        } catch (RegistrationException e) {
            log.error("Error get service", e);
        }
        if (client != null) {
            return client.getClientService(MgnlTegramBotRestService.class);
        }
        return null;
    }

    /**
     * Gets the session.
     *
     * @param workspace the workspace
     * @return the session
     */
    private Session getSession(String workspace) {
        log.debug("getSession ");
        try {
            return LifeTimeJCRSessionUtil.getSession(workspace);
        } catch (RepositoryException e) {
            log.error("Error obtain session", e);
            return null;
        }
    }

    /**
     * Gets the content stream.
     *
     * @param idAsset the id asset
     * @return the content stream
     */
    public InputStream getContentStream(String idAsset) {
        log.debug("getContentStream ");
        try {
            Session session = getSession(WORKSPACE_DAM);
            if (session != null) {
                Node asset = session.getNodeByIdentifier(idAsset);
                Node node = AssetNodeTypes.AssetResource.getResourceNodeFromAsset(asset);
                if (node != null) {
                    return node.getProperty(AssetNodeTypes.AssetResource.DATA).getBinary().getStream();
                }
            }
        } catch (RepositoryException e) {
            log.error("Error obtain asset", e);
        }
        return null;
    }
}
