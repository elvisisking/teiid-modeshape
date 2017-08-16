/*
 * JBoss, Home of Professional Open Source.
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package org.teiid.modeshape.sequencer.vdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.modeshape.common.util.CheckArg;
import org.modeshape.common.util.StringUtil;
import org.teiid.modeshape.util.TriState;

/**
 * A simple POJO that is used to represent the information for a data role read in from a VDB manifest ("vdb.xml").
 */
public class VdbDataRole implements Comparable<VdbDataRole> {

    private final String name;
    private boolean anyAuthenticated;
    private boolean allowCreateTempTables;
    private boolean grantAll;
    private String description;
    private final List<Permission> permissions = new ArrayList<VdbDataRole.Permission>();
    private final List<String> roleNames = new ArrayList<String>();

    /**
     * @param name the data role name (cannot be <code>null</code> or empty)
     */
    public VdbDataRole( final String name ) {
        CheckArg.isNotEmpty(name, "name");
        this.name = name;
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo( final VdbDataRole that ) {
        CheckArg.isNotNull(that, "that");

        if (this == that) {
            return 0;
        }

        // order by name
        return this.name.compareTo(that.name);
    }

    /**
     * @return the description (never <code>null</code> but can be empty)
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the mapped data role names
     */
    public List<String> getMappedRoleNames() {
        return this.roleNames;
    }

    /**
     * @return the data role name (never <code>null</code> or empty)
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the data role permissions (never <code>null</code> but can be empty)
     */
    public List<Permission> getPermissions() {
        return this.permissions;
    }

    /**
     * @return <code>true</code> if data role can create temp tables
     */
    public boolean isAllowCreateTempTables() {
        return this.allowCreateTempTables;
    }

    /**
     * @return <code>true</code> if data role has any authenticated
     */
    public boolean isAnyAuthenticated() {
        return this.anyAuthenticated;
    }

    /**
     * @return <code>true</code> if data role has grant all
     */
    public boolean isGrantAll() {
        return this.grantAll;
    }

    /**
     * @param newValue the new value for allowCreateTempTables
     */
    public void setAllowCreateTempTables( final boolean newValue ) {
        this.allowCreateTempTables = newValue;
    }

    /**
     * @param newValue the new value for anyAuthenticated
     */
    public void setAnyAuthenticated( final boolean newValue ) {
        this.anyAuthenticated = newValue;
    }

    /**
     * @param newValue the new description value (can be <code>null</code> or empty)
     */
    public void setDescription( final String newValue ) {
        this.description = StringUtil.isBlank(newValue) ? "" : newValue;
    }

    /**
     * @param grantAll
     */
    public void setGrantAll(boolean grantAll) {
        this.grantAll = grantAll;
    }

    /**
     * A simple POJO that is used to represent one data role permission found in the VDB manifest ("vdb.xml").
     */
    public class Permission {

        private TriState alter;
        private TriState create;
        private TriState delete;
        private TriState execute;
        private TriState read;
        private final String resourceName;
        private TriState update;
        private TriState language;
        private List<Condition> conditions = Collections.emptyList();
        private List<Mask> masks = Collections.emptyList();

        /**
         * @param resourceName the resource name associated with the permission (cannot be <code>null</code> or empty)
         */
        public Permission( final String resourceName ) {
            CheckArg.isNotEmpty(resourceName, "resourceName");
            this.resourceName = resourceName;
        }

        /**
         * @param newValue the new allow-alter value (can be <code>null</code> to unset)
         */
        public void allowAlter( final TriState newValue ) {
            this.alter = ( newValue == null ) ? TriState.UNSET : newValue;
        }

        /**
         * @param newValue the new allow-create value (can be <code>null</code> to unset)
         */
        public void allowCreate( final TriState newValue ) {
            this.create = ( newValue == null ) ? TriState.UNSET : newValue;
        }

        /**
         * @param newValue the new allow-delete value (can be <code>null</code> to unset)
         */
        public void allowDelete( final TriState newValue ) {
            this.delete = ( newValue == null ) ? TriState.UNSET : newValue;
        }

        /**
         * @param newValue the new allow-execute value (can be <code>null</code> to unset)
         */
        public void allowExecute( final TriState newValue ) {
            this.execute = ( newValue == null ) ? TriState.UNSET : newValue;
        }

        /**
         * @param newValue the new allow-read value (can be <code>null</code> to unset)
         */
        public void allowRead( final TriState newValue ) {
            this.read = ( newValue == null ) ? TriState.UNSET : newValue;
        }

        /**
         * @param newValue the new allow-update value (can be <code>null</code> to unset)
         */
        public void allowUpdate( final TriState newValue ) {
            this.update = ( newValue == null ) ? TriState.UNSET : newValue;
        }

        /**
         * @param newValue the new allow-language value (can be <code>null</code> to unset)
         */
        public void allowLanguage( final TriState newValue ) {
            this.language = ( newValue == null ) ? TriState.UNSET : newValue;
        }

        /**
         * @return the current value of the allow-alter property (never <code>null</code>)
         */
        public TriState canAlter() {
            return this.alter;
        }

        /**
         * @return the current value of the allow-create property (never <code>null</code>)
         */
        public TriState canCreate() {
            return this.create;
        }

        /**
         * @return the current value of the allow-delete property (never <code>null</code>)
         */
        public TriState canDelete() {
            return this.delete;
        }

        /**
         * @return the current value of the allow-execute property (never <code>null</code>)
         */
        public TriState canExecute() {
            return this.execute;
        }

        /**
         * @return the current value of the allow-read property (never <code>null</code>)
         */
        public TriState canRead() {
            return this.read;
        }

        /**
         * @return the current value of the allow-update property (never <code>null</code>)
         */
        public TriState canUpdate() {
            return this.update;
        }

        /**
         * @return the current value of the allow-language property (never <code>null</code>)
         */
        public TriState useLanguage() {
            return this.language;
        }

        /**
         * @return the resource name associated with the permission (never <code>null</code> or empty)
         */
        public String getResourceName() {
            return this.resourceName;
        }

        /**
         * @return the conditions
         */
        public List<Condition> getConditions() {
            return this.conditions;
        }

        /**
         * @param conditions associated with the permission
         */
        public void setConditions(List<Condition> conditions) {
            this.conditions = conditions;
        }

        /**
         * @return the masks
         */
        public List<Mask> getMasks() {
            return this.masks;
        }

        /**
         * @param masks associated with the permission
         */
        public void setMasks(List<Mask> masks) {
            this.masks = masks;
        }
    }

    /**
     * A simple POJO that is used to represent a data role permission's single condition found in the
     * VDB manifest ("vdb.xml").
     */
    public class Condition {

        private boolean constraint = true;

        private String rule;

        /**
         * @return constraint flag
         */
        public boolean isConstraint() {
            return constraint;
        }

        /**
         * @param constraint the constraint flag
         */
        public void setConstraint(boolean constraint) {
            this.constraint = constraint;
        }

        /**
         * @return the rule
         */
        public String getRule() {
            return this.rule;
        }

        /**
         * @param rule
         */
        public void setRule(String rule) {
            this.rule = rule;
        }
    }

    /**
     * A simple POJO that is used to represent a data role permission's single mask found in the
     * VDB manifest ("vdb.xml").
     */
    public class Mask {

        private int order;

        private String rule;

        /**
         * @return order value
         */
        public int getOrder() {
            return order;
        }

        /**
         * @param order the order value
         */
        public void setOrder(int order) {
            this.order = order;
        }

        /**
         * @return the rule
         */
        public String getRule() {
            return this.rule;
        }

        /**
         * @param rule
         */
        public void setRule(String rule) {
            this.rule = rule;
        }
    }
}
