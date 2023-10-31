package uz.xbakhromjon.auth.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static uz.xbakhromjon.auth.security.AuthoritiesConstants.*;

@Getter
@RequiredArgsConstructor
public enum ERole {
    ROLE_USER(Set.of(
            CREATE_ANNOUNCEMENT,
            UPDATE_ANNOUNCEMENT,
            GET_ONE_ANNOUNCEMENT,
            FILTER_PUBLIC_ANNOUNCEMENT_WITH_DETAILED_INFORMATION,
            FILTER_PUBLIC_ANNOUNCEMENT_WITH_HEADER_INFORMATION,
            DELETE_ANNOUNCEMENT,
            PURCHASE_ANNOUNCEMENT,
            FILTER_PURCHASED_ANNOUNCEMENT,
            FILTER_SEEN_ANNOUNCEMENT,
            PUBLIC_ANNOUNCEMENT_FILTERS,
            FILTER_PUBLIC_ANNOUNCEMENT_WITH_COUNT,
            UPDATE_ACCOUNT,
            GET_ACCOUNT,
            PARTIAL_UPDATE_ACCOUNT,
            DELETE_ACCOUNT
    ));

    private final Set<String> defaultAuthorities;
}
