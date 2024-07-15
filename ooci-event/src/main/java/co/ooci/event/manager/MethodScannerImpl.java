/*
 *     KookBC -- The Kook Bot Client & JKook API standard implementation for Java.
 *     Copyright (C) 2022 - 2023 KookBC contributors
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published
 *     by the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package co.ooci.event.manager;

import lombok.NonNull;
import net.kyori.event.PostOrders;
import net.kyori.event.method.MethodScanner;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class MethodScannerImpl implements MethodScanner<OociListener> {
    public static final MethodScannerImpl INSTANCE = new MethodScannerImpl();

    private MethodScannerImpl() {
    }

    @Override
    public boolean shouldRegister(@NonNull OociListener listener, Method method) {
        return Modifier.isPublic(method.getModifiers()) && method.isAnnotationPresent(OociEventHandler.class);
    }

    @Override
    public int postOrder(@NonNull OociListener listener, Method method) {
        return method.getAnnotation(OociEventHandler.class).internal() ? PostOrders.EARLY : PostOrders.NORMAL;
    }

    @Override
    public boolean consumeCancelledEvents(@NonNull OociListener listener, @NonNull Method method) {
        return false;
    }

}