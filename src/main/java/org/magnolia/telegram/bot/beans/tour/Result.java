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
package org.magnolia.telegram.bot.beans.tour;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The Class Result.
 */
public class Result {

    /** The path. */
    @SerializedName("@path")
    @Expose
    private String path;
    
    /** The id. */
    @SerializedName("@id")
    @Expose
    private String id;
    
    /** The node type. */
    @SerializedName("@nodeType")
    @Expose
    private String nodeType;
    
    /** The jcr created by. */
    @SerializedName("jcr:createdBy")
    @Expose
    private String jcrCreatedBy;
    
    /** The is featured. */
    @SerializedName("isFeatured")
    @Expose
    private String isFeatured;
    
    /** The name. */
    @SerializedName("name")
    @Expose
    private String name;
    
    /** The mgnl last activated. */
    @SerializedName("mgnl:lastActivated")
    @Expose
    private String mgnlLastActivated;
    
    /** The mgnl created. */
    @SerializedName("mgnl:created")
    @Expose
    private String mgnlCreated;
    
    /** The description. */
    @SerializedName("description")
    @Expose
    private String description;
    
    /** The location. */
    @SerializedName("location")
    @Expose
    private String location;
    
    /** The tour types. */
    @SerializedName("tourTypes")
    @Expose
    private List<String> tourTypes = null;
    
    /** The author. */
    @SerializedName("author")
    @Expose
    private String author;
    
    /** The mgnl activation status. */
    @SerializedName("mgnl:activationStatus")
    @Expose
    private String mgnlActivationStatus;
    
    /** The body. */
    @SerializedName("body")
    @Expose
    private String body;
    
    /** The mgnl last activated by. */
    @SerializedName("mgnl:lastActivatedBy")
    @Expose
    private String mgnlLastActivatedBy;
    
    /** The jcr created. */
    @SerializedName("jcr:created")
    @Expose
    private String jcrCreated;
    
    /** The destination. */
    @SerializedName("destination")
    @Expose
    private List<String> destination = null;
    
    /** The mgnl created by. */
    @SerializedName("mgnl:createdBy")
    @Expose
    private String mgnlCreatedBy;
    
    /** The duration. */
    @SerializedName("duration")
    @Expose
    private String duration;
    
    /** The mgnl comment. */
    @SerializedName("mgnl:comment")
    @Expose
    private String mgnlComment;
    
    /** The mgnl last modified. */
    @SerializedName("mgnl:lastModified")
    @Expose
    private String mgnlLastModified;
    
    /** The image. */
    @SerializedName("image")
    @Expose
    private String image;
    
    /** The mgnl last modified by. */
    @SerializedName("mgnl:lastModifiedBy")
    @Expose
    private String mgnlLastModifiedBy;
    
    /** The nodes. */
    @SerializedName("@nodes")
    @Expose
    private List<Object> nodes = null;

    /**
     * Gets the path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path.
     *
     * @param path the new path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the node type.
     *
     * @return the node type
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * Sets the node type.
     *
     * @param nodeType the new node type
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * Gets the jcr created by.
     *
     * @return the jcr created by
     */
    public String getJcrCreatedBy() {
        return jcrCreatedBy;
    }

    /**
     * Sets the jcr created by.
     *
     * @param jcrCreatedBy the new jcr created by
     */
    public void setJcrCreatedBy(String jcrCreatedBy) {
        this.jcrCreatedBy = jcrCreatedBy;
    }

    /**
     * Gets the checks if is featured.
     *
     * @return the checks if is featured
     */
    public String getIsFeatured() {
        return isFeatured;
    }

    /**
     * Sets the checks if is featured.
     *
     * @param isFeatured the new checks if is featured
     */
    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the mgnl last activated.
     *
     * @return the mgnl last activated
     */
    public String getMgnlLastActivated() {
        return mgnlLastActivated;
    }

    /**
     * Sets the mgnl last activated.
     *
     * @param mgnlLastActivated the new mgnl last activated
     */
    public void setMgnlLastActivated(String mgnlLastActivated) {
        this.mgnlLastActivated = mgnlLastActivated;
    }

    /**
     * Gets the mgnl created.
     *
     * @return the mgnl created
     */
    public String getMgnlCreated() {
        return mgnlCreated;
    }

    /**
     * Sets the mgnl created.
     *
     * @param mgnlCreated the new mgnl created
     */
    public void setMgnlCreated(String mgnlCreated) {
        this.mgnlCreated = mgnlCreated;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location.
     *
     * @param location the new location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the tour types.
     *
     * @return the tour types
     */
    public List<String> getTourTypes() {
        return tourTypes;
    }

    /**
     * Sets the tour types.
     *
     * @param tourTypes the new tour types
     */
    public void setTourTypes(List<String> tourTypes) {
        this.tourTypes = tourTypes;
    }

    /**
     * Gets the author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     *
     * @param author the new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the mgnl activation status.
     *
     * @return the mgnl activation status
     */
    public String getMgnlActivationStatus() {
        return mgnlActivationStatus;
    }

    /**
     * Sets the mgnl activation status.
     *
     * @param mgnlActivationStatus the new mgnl activation status
     */
    public void setMgnlActivationStatus(String mgnlActivationStatus) {
        this.mgnlActivationStatus = mgnlActivationStatus;
    }

    /**
     * Gets the body.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body.
     *
     * @param body the new body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets the mgnl last activated by.
     *
     * @return the mgnl last activated by
     */
    public String getMgnlLastActivatedBy() {
        return mgnlLastActivatedBy;
    }

    /**
     * Sets the mgnl last activated by.
     *
     * @param mgnlLastActivatedBy the new mgnl last activated by
     */
    public void setMgnlLastActivatedBy(String mgnlLastActivatedBy) {
        this.mgnlLastActivatedBy = mgnlLastActivatedBy;
    }

    /**
     * Gets the jcr created.
     *
     * @return the jcr created
     */
    public String getJcrCreated() {
        return jcrCreated;
    }

    /**
     * Sets the jcr created.
     *
     * @param jcrCreated the new jcr created
     */
    public void setJcrCreated(String jcrCreated) {
        this.jcrCreated = jcrCreated;
    }

    /**
     * Gets the destination.
     *
     * @return the destination
     */
    public List<String> getDestination() {
        return destination;
    }

    /**
     * Sets the destination.
     *
     * @param destination the new destination
     */
    public void setDestination(List<String> destination) {
        this.destination = destination;
    }

    /**
     * Gets the mgnl created by.
     *
     * @return the mgnl created by
     */
    public String getMgnlCreatedBy() {
        return mgnlCreatedBy;
    }

    /**
     * Sets the mgnl created by.
     *
     * @param mgnlCreatedBy the new mgnl created by
     */
    public void setMgnlCreatedBy(String mgnlCreatedBy) {
        this.mgnlCreatedBy = mgnlCreatedBy;
    }

    /**
     * Gets the duration.
     *
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration.
     *
     * @param duration the new duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Gets the mgnl comment.
     *
     * @return the mgnl comment
     */
    public String getMgnlComment() {
        return mgnlComment;
    }

    /**
     * Sets the mgnl comment.
     *
     * @param mgnlComment the new mgnl comment
     */
    public void setMgnlComment(String mgnlComment) {
        this.mgnlComment = mgnlComment;
    }

    /**
     * Gets the mgnl last modified.
     *
     * @return the mgnl last modified
     */
    public String getMgnlLastModified() {
        return mgnlLastModified;
    }

    /**
     * Sets the mgnl last modified.
     *
     * @param mgnlLastModified the new mgnl last modified
     */
    public void setMgnlLastModified(String mgnlLastModified) {
        this.mgnlLastModified = mgnlLastModified;
    }

    /**
     * Gets the image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image.
     *
     * @param image the new image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the mgnl last modified by.
     *
     * @return the mgnl last modified by
     */
    public String getMgnlLastModifiedBy() {
        return mgnlLastModifiedBy;
    }

    /**
     * Sets the mgnl last modified by.
     *
     * @param mgnlLastModifiedBy the new mgnl last modified by
     */
    public void setMgnlLastModifiedBy(String mgnlLastModifiedBy) {
        this.mgnlLastModifiedBy = mgnlLastModifiedBy;
    }

    /**
     * Gets the nodes.
     *
     * @return the nodes
     */
    public List<Object> getNodes() {
        return nodes;
    }

    /**
     * Sets the nodes.
     *
     * @param nodes the new nodes
     */
    public void setNodes(List<Object> nodes) {
        this.nodes = nodes;
    }

}
