/*
 * (c) Copyright 2019 Palantir Technologies Inc. All rights reserved.
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

package com.palantir.conjure.java.services.dialogue;

import com.palantir.conjure.spec.AuthType;
import com.palantir.conjure.visitor.AuthTypeVisitor;
import com.palantir.tokens.auth.AuthHeader;
import com.squareup.javapoet.ParameterSpec;

public final class Auth {
    public static final String AUTH_HEADER_NAME = "Authorization";
    public static final String AUTH_HEADER_PARAM_NAME = "authHeader";

    private Auth() {}

    public static void verifyAuthTypeIsHeader(AuthType authType) {
        if (!authType.accept(AuthTypeVisitor.IS_HEADER)) {
            throw new UnsupportedOperationException("AuthType not supported by conjure-dialogue: " + authType);
        }
    }

    public static ParameterSpec authParam(AuthType auth) {
        verifyAuthTypeIsHeader(auth);
        return ParameterSpec.builder(AuthHeader.class, AUTH_HEADER_PARAM_NAME).build();
    }
}
