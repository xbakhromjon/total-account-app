package uz.xbakhromjon.auth.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String CREATE_CUSTOMER = "CREATE_CUSTOMER";
    public static final String DELETE_CUSTOMER = "DELETE_CUSTOMER";

    // announcements

    // default user authorities
    public static final String CREATE_ANNOUNCEMENT = "CREATE_ANNOUNCEMENT";
    public static final String UPDATE_ANNOUNCEMENT = "UPDATE_ANNOUNCEMENT";
    public static final String GET_ONE_ANNOUNCEMENT = "GET_ONE_ANNOUNCEMENT";
    public static final String FILTER_PUBLIC_ANNOUNCEMENT_WITH_DETAILED_INFORMATION = "FILTER_PUBLIC_ANNOUNCEMENT_WITH_DETAILED_INFORMATION";
    public static final String FILTER_PUBLIC_ANNOUNCEMENT_WITH_HEADER_INFORMATION = "FILTER_PUBLIC_ANNOUNCEMENT_WITH_HEADER_INFORMATION";
    public static final String DELETE_ANNOUNCEMENT = "DELETE_ANNOUNCEMENT";
    public static final String PURCHASE_ANNOUNCEMENT = "PURCHASE_ANNOUNCEMENT";
    public static final String FILTER_PURCHASED_ANNOUNCEMENT = "FILTER_PURCHASED_ANNOUNCEMENT";
    public static final String FILTER_SEEN_ANNOUNCEMENT = "FILTER_SEEN_ANNOUNCEMENT";
    public static final String INCREASE_SHARE_COUNT = "INCREASE_SHARE_COUNT";

    // default moderator authorities
    public static final String ACCEPT_USER_ANNOUNCEMENT = "ACCEPT_USER_ANNOUNCEMENT";
    public static final String REJECT_USER_ANNOUNCEMENT = "REJECT_USER_ANNOUNCEMENT";
    public static final String CHANGE_STATUS_USER_ANNOUNCEMENT = "CHANGE_STATUS_USER_ANNOUNCEMENT";
    public static final String CREATE_USER_ANNOUNCEMENT = "CREATE_USER_ANNOUNCEMENT";
    public static final String UPDATE_USER_ANNOUNCEMENT = "UPDATE_USER_ANNOUNCEMENT";
    public static final String DELETE_USER_ANNOUNCEMENT = "DELETE_USER_ANNOUNCEMENT";
    public static final String GET_ONE_USER_ANNOUNCEMENT = "GET_ONE_USER_ANNOUNCEMENT";
    public static final String FILTER_ALL_ANNOUNCEMENT_WITH_HEADER_INFORMATION = "FILTER_ALL_ANNOUNCEMENT_WITH_HEADER_INFORMATION";
    public static final String GET_ALL_ANNOUNCEMENT_STATISTICS = "GET_ALL_ANNOUNCEMENT_STATISTICS";
    public static final String GET_USER_ANNOUNCEMENT_STATISTICS = "GET_USER_ANNOUNCEMENT_STATISTICS";
    public static final String DO_TOP_USER_ANNOUNCEMENT = "DO_TOP_USER_ANNOUNCEMENT";

    // general
    public static final String GET_DRAFT_ANNOUNCEMENT = "GET_DRAFT_ANNOUNCEMENT";
    public static final String DO_TOP_ANNOUNCEMENT = "DO_TOP_ANNOUNCEMENT";
    public static final String PUBLIC_ANNOUNCEMENT_FILTERS = "PUBLIC_ANNOUNCEMENT_FILTERS";
    public static final String FILTER_PUBLIC_ANNOUNCEMENT_WITH_COUNT = "FILTER_PUBLIC_ANNOUNCEMENT_WITH_COUNT";


    // USER RESOURCE
    public static final String CREATE_USER = "CREATE_USER";
    public static final String UPDATE_USER = "UPDATE_USER";
    public static final String GET_USER = "GET_USER";
    public static final String FILTER_USER = "FILTER_USER";
    public static final String DELETE_USER = "DELETE_USER";
    public static final String CHANGE_USER_PASSWORD = "CHANGE_USER_PASSWORD";
    public static final String CHANGE_USER_PHONE_NUMBER = "CHANGE_USER_PHONE_NUMBER";
    public static final String BLOCK_USER = "BLOCK_USER";
    public static final String UNBLOCK_USER = "UNBLOCK_USER";
    public static final String GET_USER_STATISTICS = "GET_USER_STATISTICS";

    // account
    public static final String UPDATE_ACCOUNT = "UPDATE_ACCOUNT";
    public static final String GET_ACCOUNT = "GET_ACCOUNT";
    public static final String PARTIAL_UPDATE_ACCOUNT = "PARTIAL_UPDATE_ACCOUNT";
    public static final String DELETE_ACCOUNT = "DELETE_ACCOUNT";

    private AuthoritiesConstants() {
    }
}
