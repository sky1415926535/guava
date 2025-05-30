/*
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.common.collect;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Platform.getDeclaringClassOrObjectForJ2cl;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Iterator;

@GwtCompatible(emulated = true)
@J2ktIncompatible
public final class EnumMultiset<E extends Enum<E>> extends AbstractMapBasedMultiset<E>
    implements Serializable {
  public static <E extends Enum<E>> EnumMultiset<E> create(Class<E> type) {
    return new EnumMultiset<>(new EnumMap<E, Count>(type));
  }

  public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> elements) {
    Iterator<E> iterator = elements.iterator();
    checkArgument(iterator.hasNext(), "EnumMultiset constructor passed empty Iterable");
    EnumMap<E, Count> map = new EnumMap<>(getDeclaringClassOrObjectForJ2cl(iterator.next()));
    EnumMultiset<E> multiset = new EnumMultiset<>(map);
    Iterables.addAll(multiset, elements);
    return multiset;
  }

  public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> elements, Class<E> type) {
    EnumMultiset<E> result = create(type);
    Iterables.addAll(result, elements);
    return result;
  }

  /** Creates an empty {@code EnumMultiset}. */
  private EnumMultiset(EnumMap<E, Count> map) {
    super(map);
  }
}
