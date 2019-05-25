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
package org.magnolia.telegram.bot.beans.asset;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The Class Result.
 */
public class Result {

    @SerializedName("@path")
    @Expose
    private String path;
    @SerializedName("@id")
    @Expose
    private String id;
    @SerializedName("@nodeType")
    @Expose
    private String nodeType;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("jcr:createdBy")
    @Expose
    private String jcrCreatedBy;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mgnl:lastActivatedBy")
    @Expose
    private String mgnlLastActivatedBy;
    @SerializedName("mgnl:lastActivated")
    @Expose
    private String mgnlLastActivated;
    @SerializedName("jcr:created")
    @Expose
    private String jcrCreated;
    @SerializedName("mgnl:created")
    @Expose
    private String mgnlCreated;
    @SerializedName("mgnl:createdBy")
    @Expose
    private String mgnlCreatedBy;
    @SerializedName("mgnl:lastModified")
    @Expose
    private String mgnlLastModified;
    @SerializedName("mgnl:activationStatus")
    @Expose
    private String mgnlActivationStatus;
    @SerializedName("mgnl:lastModifiedBy")
    @Expose
    private String mgnlLastModifiedBy;
    @SerializedName("@nodes")
    @Expose
    private List<Object> nodes = null;
    @SerializedName("master")
    @Expose
    private String master;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getJcrCreatedBy() {
        return jcrCreatedBy;
    }

    public void setJcrCreatedBy(String jcrCreatedBy) {
        this.jcrCreatedBy = jcrCreatedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMgnlLastActivatedBy() {
        return mgnlLastActivatedBy;
    }

    public void setMgnlLastActivatedBy(String mgnlLastActivatedBy) {
        this.mgnlLastActivatedBy = mgnlLastActivatedBy;
    }

    public String getMgnlLastActivated() {
        return mgnlLastActivated;
    }

    public void setMgnlLastActivated(String mgnlLastActivated) {
        this.mgnlLastActivated = mgnlLastActivated;
    }

    public String getJcrCreated() {
        return jcrCreated;
    }

    public void setJcrCreated(String jcrCreated) {
        this.jcrCreated = jcrCreated;
    }

    public String getMgnlCreated() {
        return mgnlCreated;
    }

    public void setMgnlCreated(String mgnlCreated) {
        this.mgnlCreated = mgnlCreated;
    }

    public String getMgnlCreatedBy() {
        return mgnlCreatedBy;
    }

    public void setMgnlCreatedBy(String mgnlCreatedBy) {
        this.mgnlCreatedBy = mgnlCreatedBy;
    }

    public String getMgnlLastModified() {
        return mgnlLastModified;
    }

    public void setMgnlLastModified(String mgnlLastModified) {
        this.mgnlLastModified = mgnlLastModified;
    }

    public String getMgnlActivationStatus() {
        return mgnlActivationStatus;
    }

    public void setMgnlActivationStatus(String mgnlActivationStatus) {
        this.mgnlActivationStatus = mgnlActivationStatus;
    }

    public String getMgnlLastModifiedBy() {
        return mgnlLastModifiedBy;
    }

    public void setMgnlLastModifiedBy(String mgnlLastModifiedBy) {
        this.mgnlLastModifiedBy = mgnlLastModifiedBy;
    }

    public List<Object> getNodes() {
        return nodes;
    }

    public void setNodes(List<Object> nodes) {
        this.nodes = nodes;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

}
