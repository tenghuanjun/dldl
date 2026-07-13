package com.cy.yyjia.zhe28;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.cy.yyjia.zhe28.databinding.ActivityAddressBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityAlipayBindBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityAuthBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityBaofuBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityBbsDetail2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityBbsDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityBbsEditBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityBbsMessageBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityBbsSearchBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityBossServerBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityCancellationBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityCardBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityChampionshipBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityChangePasswordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityCommentDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityCommentEditBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityDailyCouponBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityDailyTaskBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityDealDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityDealListBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityDealRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityDealSellBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityDealSellInfoBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityDevelopBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityDownloadBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityEmptyBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityEventDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityFeedbackBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityFragmentBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityFudaiIndexBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityGameCouponBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityGameDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityGameReport2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityGameReportBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityGiftDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityGmBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityGmPayBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityGmTransBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityGmTransPayBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityImageBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityInfoBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityInvite3BindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityInviteWithdrewBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityItemSellBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityItemSellGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityItemSellRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityItemTradeDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityJfRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityLoginBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityLotteryBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityLotteryRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityMainBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityMessageBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityMonthCardBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityMonthlyTaskBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityMyMoneyBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityMyWelfareBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityNoviceTaskBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityNoviceWelfareBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityPhoneBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityPinBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityPtbBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityPtbDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityQiandaoBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityQiandaoRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityQuickLoginBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityRecordDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityRecycleBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityRegisterBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityRvBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityRvTabBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivitySafeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivitySanbaoBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivitySearchBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityServiceBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityServiceDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivitySettingBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivitySplashBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivitySubscribeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityTaskHallBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityTopicDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityTrumpetBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityUserBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityVideoBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityVipBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityVipCouponBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityWeb2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityWebBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityWebPayBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityWelfareEventDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityWithdrewBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityYunBuyBindingImpl;
import com.cy.yyjia.zhe28.databinding.ActivityYunPlayBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogBbsGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogBbsReplyBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogBbsSignBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogBossServerBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogBottomTip2BindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogBottomTipBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogCommentBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogConfirmBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogDailyCouponReceiveBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogDealBuyNoticeBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogDealDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogDealDickerBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogDealFilterBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogDealOffsetBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogDealPlayedBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogDealRecordTypeBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogDealSellNotice1BindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogGameUpdateDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogGiftCodeBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogGmGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogGmItemsBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogGmLogBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogGmRolesBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogHomeBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogInviteBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogInviteGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogInviteRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogItemAdjustBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogLogoutBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogLotteryResultBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogNewGameTypeBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogNoviceWelfareBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogPayBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogPicBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogPinBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogPrivacyBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogQiandaoTaskBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogRuleBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogSelectTrumpetBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogShareBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogSqkRuleBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogTipBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogToThuntBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogTopicRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogTopicRuleBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogUnableGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogUpdateBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogVipCouponBuyBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogVipRight2BindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogVipWebBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogWaitBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogWelfare3GiftBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogWelfare3RoleBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogWithdraw2BindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogWithdrawBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogYunDeviceBindingImpl;
import com.cy.yyjia.zhe28.databinding.DialogYunGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentBaseBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentBbs2BindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentBbsBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentBbsIndexBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentCancellation1BindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentCancellation2BindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentCancellation3BindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentCancellation4BindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentDealBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentDealIndexBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentDealRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentDealSellBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentGameCommentBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentGameIntroBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentGameServerBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentGameToolBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentHallBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentHallGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentHomeBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentHomeGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentHomeList2BindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentHomeNewBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentHomeSchedule2BindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentHomeScheduleBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentImageBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentItemTradeBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentMainBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentRvBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentSanbao648BindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentScheduleBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentSubscribe2BindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentSubscribeBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentTopicDetailBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentUserBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentVipRightBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentWebBindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentWelfare3BindingImpl;
import com.cy.yyjia.zhe28.databinding.FragmentWelfare4BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsCateBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsEditContentBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsEditTagBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsMessageIndexBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsMessageOfficialBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsMessageReplyBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsSearchBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsSignBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsSignWelfareBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBbsTopBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBillBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBossServer2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemBossServerGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemCardPriceBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemCardRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemCardRewardRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemCardTitleBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemChampionshipRewardBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemChampionshipTaskBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemCommentCategoryBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemCommentContentBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemCommentTagBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDailyCouponBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealDickerBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealFilterTypeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealFunBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealHotGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealMoneyRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealPicBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealPlayedBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealRecordTypeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealRoleBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealSellBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDealSellChildBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDialogHomeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDialogInviteGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDialogYunDeviceBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemDownloadBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemFeedbackPicBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemFudaiIndexBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemFunBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameBannerVideoBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameCommentBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameCommentSubBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameCommentTopBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameCouponBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameDealBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameDetailBannerBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameDetailChatBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameDetailTagBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameEventBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameIntro1BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameIntro2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameIntro3BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameIntro4BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameIntroPicBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameSearchBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameServiceBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameToolBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameType2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameTypeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameUpdate2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameUpdateBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGameVipBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGiftBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGmGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGmItemBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGmLogBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGmRoleBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGmTitleBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGroupGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemGroupUserBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHallFilterBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHallGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHallGameTypeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeC1BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeC2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeC3BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeC4BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeCateBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeGame2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeGameHeadBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeGroupBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeLargeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeMiniBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeNewBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomePicBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeRankBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeSchedule2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeScheduleBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeScheduleHeadBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeTabBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeTypeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeVerticalBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeVideo1BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeVideo2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemHomeVideoGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemInviteListBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemInviteRankBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemInviteWithdrawRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemInviteWithdrewRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemItemSellGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemItemSellOrderBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemItemSellingBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemItemTradeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemItemTradeRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemItemTradeServerBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemJfRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemLotteryFunBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemLotteryGiftBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemLotteryGiftResultBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemLotteryRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemMainTabBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemMessageDickerBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemMessageOfficialBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemMessageUserBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemMyCouponBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemMyGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemMyGiftBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemMyVoucherBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemNoviceGameCouponBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemNoviceGameGiftBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemPermissionListBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemPicSelectBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemPointRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemPrombleBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemQiandaoBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemQiandaoRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemQiandaoTaskBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemQiandaoUserBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemRecycleAccountBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemRecycleBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemRecycleRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemReportPicBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemSanbao648BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemSanbao648CouponBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemSanbao648GiftBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemSanbaoGame2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemSanbaoGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemSanbaoMessageBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemScheduleTimeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemScheduleTypeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemSearchHistoryBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemSearchHotBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemSearchTypeBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemSelectTrumpetBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemServiceBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemServiceProblemBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemTaskBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemTaskDailyBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemTaskMonthlyBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemTopicBannerBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemTopicLotteryBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemTopicRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemTopicTaskBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemTrumpet1BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemTrumpet2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemUnableGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemVipCouponBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemVipCouponLevelBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemVipFlbBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemVipFunBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemVipGiftBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemVipGiftSmallBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemVipRightBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfare31BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfare3GameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfare3GameSmallBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfare3Gift2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfare3GiftBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfare3RoleBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfareCardBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfareEventReplyBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfareQiandaoBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfareTask2BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfareTask3BindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWelfareTaskBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemWithdrewRecordBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemYunBlockBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemYunGameBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemYunPopDeviceBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemYunPriceBindingImpl;
import com.cy.yyjia.zhe28.databinding.ItemYunTipBindingImpl;
import com.cy.yyjia.zhe28.databinding.LayoutBannerBbsBindingImpl;
import com.cy.yyjia.zhe28.databinding.LayoutBannerHomeBindingImpl;
import com.cy.yyjia.zhe28.databinding.LayoutDealAllBindingImpl;
import com.cy.yyjia.zhe28.databinding.LayoutDiscountBindingImpl;
import com.cy.yyjia.zhe28.databinding.LayoutGameDetailTabBindingImpl;
import com.cy.yyjia.zhe28.databinding.LayoutGameIconBindingImpl;
import com.cy.yyjia.zhe28.databinding.LayoutGameNameBindingImpl;
import com.cy.yyjia.zhe28.databinding.LayoutGameTag2BindingImpl;
import com.cy.yyjia.zhe28.databinding.LayoutGameTag3BindingImpl;
import com.cy.yyjia.zhe28.databinding.LayoutGameTagBindingImpl;
import com.cy.yyjia.zhe28.databinding.LayoutTabTextBindingImpl;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.lzy.okgo.model.Progress;
import com.mobile.auth.gatewayauth.Constant;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.tencent.open.SocialConstants;
import com.volcengine.common.contant.CommonConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYADDRESS = 1;
    private static final int LAYOUT_ACTIVITYALIPAYBIND = 2;
    private static final int LAYOUT_ACTIVITYAUTH = 3;
    private static final int LAYOUT_ACTIVITYBAOFU = 4;
    private static final int LAYOUT_ACTIVITYBBSDETAIL = 5;
    private static final int LAYOUT_ACTIVITYBBSDETAIL2 = 6;
    private static final int LAYOUT_ACTIVITYBBSEDIT = 7;
    private static final int LAYOUT_ACTIVITYBBSMESSAGE = 8;
    private static final int LAYOUT_ACTIVITYBBSSEARCH = 9;
    private static final int LAYOUT_ACTIVITYBOSSSERVER = 10;
    private static final int LAYOUT_ACTIVITYCANCELLATION = 11;
    private static final int LAYOUT_ACTIVITYCARD = 12;
    private static final int LAYOUT_ACTIVITYCHAMPIONSHIP = 13;
    private static final int LAYOUT_ACTIVITYCHANGEPASSWORD = 14;
    private static final int LAYOUT_ACTIVITYCOMMENTDETAIL = 15;
    private static final int LAYOUT_ACTIVITYCOMMENTEDIT = 16;
    private static final int LAYOUT_ACTIVITYDAILYCOUPON = 17;
    private static final int LAYOUT_ACTIVITYDAILYTASK = 18;
    private static final int LAYOUT_ACTIVITYDEALDETAIL = 19;
    private static final int LAYOUT_ACTIVITYDEALLIST = 20;
    private static final int LAYOUT_ACTIVITYDEALRECORD = 21;
    private static final int LAYOUT_ACTIVITYDEALSELL = 22;
    private static final int LAYOUT_ACTIVITYDEALSELLINFO = 23;
    private static final int LAYOUT_ACTIVITYDEVELOP = 24;
    private static final int LAYOUT_ACTIVITYDOWNLOAD = 25;
    private static final int LAYOUT_ACTIVITYEMPTY = 26;
    private static final int LAYOUT_ACTIVITYEVENTDETAIL = 27;
    private static final int LAYOUT_ACTIVITYFEEDBACK = 28;
    private static final int LAYOUT_ACTIVITYFRAGMENT = 29;
    private static final int LAYOUT_ACTIVITYFUDAIINDEX = 30;
    private static final int LAYOUT_ACTIVITYGAMECOUPON = 31;
    private static final int LAYOUT_ACTIVITYGAMEDETAIL = 32;
    private static final int LAYOUT_ACTIVITYGAMEREPORT = 33;
    private static final int LAYOUT_ACTIVITYGAMEREPORT2 = 34;
    private static final int LAYOUT_ACTIVITYGIFTDETAIL = 35;
    private static final int LAYOUT_ACTIVITYGM = 36;
    private static final int LAYOUT_ACTIVITYGMPAY = 37;
    private static final int LAYOUT_ACTIVITYGMTRANS = 38;
    private static final int LAYOUT_ACTIVITYGMTRANSPAY = 39;
    private static final int LAYOUT_ACTIVITYIMAGE = 40;
    private static final int LAYOUT_ACTIVITYINFO = 41;
    private static final int LAYOUT_ACTIVITYINVITE3 = 42;
    private static final int LAYOUT_ACTIVITYINVITEWITHDREW = 43;
    private static final int LAYOUT_ACTIVITYITEMSELL = 44;
    private static final int LAYOUT_ACTIVITYITEMSELLGAME = 45;
    private static final int LAYOUT_ACTIVITYITEMSELLRECORD = 46;
    private static final int LAYOUT_ACTIVITYITEMTRADEDETAIL = 47;
    private static final int LAYOUT_ACTIVITYJFRECORD = 48;
    private static final int LAYOUT_ACTIVITYLOGIN = 49;
    private static final int LAYOUT_ACTIVITYLOTTERY = 50;
    private static final int LAYOUT_ACTIVITYLOTTERYRECORD = 51;
    private static final int LAYOUT_ACTIVITYMAIN = 52;
    private static final int LAYOUT_ACTIVITYMESSAGE = 53;
    private static final int LAYOUT_ACTIVITYMONTHCARD = 54;
    private static final int LAYOUT_ACTIVITYMONTHLYTASK = 55;
    private static final int LAYOUT_ACTIVITYMYMONEY = 56;
    private static final int LAYOUT_ACTIVITYMYWELFARE = 57;
    private static final int LAYOUT_ACTIVITYNOVICETASK = 58;
    private static final int LAYOUT_ACTIVITYNOVICEWELFARE = 59;
    private static final int LAYOUT_ACTIVITYPHONE = 60;
    private static final int LAYOUT_ACTIVITYPIN = 61;
    private static final int LAYOUT_ACTIVITYPTB = 62;
    private static final int LAYOUT_ACTIVITYPTBDETAIL = 63;
    private static final int LAYOUT_ACTIVITYQIANDAO = 64;
    private static final int LAYOUT_ACTIVITYQIANDAORECORD = 65;
    private static final int LAYOUT_ACTIVITYQUICKLOGIN = 66;
    private static final int LAYOUT_ACTIVITYRECORDDETAIL = 67;
    private static final int LAYOUT_ACTIVITYRECYCLE = 68;
    private static final int LAYOUT_ACTIVITYREGISTER = 69;
    private static final int LAYOUT_ACTIVITYRV = 70;
    private static final int LAYOUT_ACTIVITYRVTAB = 71;
    private static final int LAYOUT_ACTIVITYSAFE = 72;
    private static final int LAYOUT_ACTIVITYSANBAO = 73;
    private static final int LAYOUT_ACTIVITYSEARCH = 74;
    private static final int LAYOUT_ACTIVITYSERVICE = 75;
    private static final int LAYOUT_ACTIVITYSERVICEDETAIL = 76;
    private static final int LAYOUT_ACTIVITYSETTING = 77;
    private static final int LAYOUT_ACTIVITYSPLASH = 78;
    private static final int LAYOUT_ACTIVITYSUBSCRIBE = 79;
    private static final int LAYOUT_ACTIVITYTASKHALL = 80;
    private static final int LAYOUT_ACTIVITYTOPICDETAIL = 81;
    private static final int LAYOUT_ACTIVITYTRUMPET = 82;
    private static final int LAYOUT_ACTIVITYUSER = 83;
    private static final int LAYOUT_ACTIVITYVIDEO = 84;
    private static final int LAYOUT_ACTIVITYVIP = 85;
    private static final int LAYOUT_ACTIVITYVIPCOUPON = 86;
    private static final int LAYOUT_ACTIVITYWEB = 87;
    private static final int LAYOUT_ACTIVITYWEB2 = 88;
    private static final int LAYOUT_ACTIVITYWEBPAY = 89;
    private static final int LAYOUT_ACTIVITYWELFAREEVENTDETAIL = 90;
    private static final int LAYOUT_ACTIVITYWITHDREW = 91;
    private static final int LAYOUT_ACTIVITYYUNBUY = 92;
    private static final int LAYOUT_ACTIVITYYUNPLAY = 93;
    private static final int LAYOUT_DIALOGBBSGAME = 94;
    private static final int LAYOUT_DIALOGBBSREPLY = 95;
    private static final int LAYOUT_DIALOGBBSSIGN = 96;
    private static final int LAYOUT_DIALOGBOSSSERVER = 97;
    private static final int LAYOUT_DIALOGBOTTOMTIP = 98;
    private static final int LAYOUT_DIALOGBOTTOMTIP2 = 99;
    private static final int LAYOUT_DIALOGCOMMENT = 100;
    private static final int LAYOUT_DIALOGCONFIRM = 101;
    private static final int LAYOUT_DIALOGDAILYCOUPONRECEIVE = 102;
    private static final int LAYOUT_DIALOGDEALBUYNOTICE = 103;
    private static final int LAYOUT_DIALOGDEALDETAIL = 104;
    private static final int LAYOUT_DIALOGDEALDICKER = 105;
    private static final int LAYOUT_DIALOGDEALFILTER = 106;
    private static final int LAYOUT_DIALOGDEALOFFSET = 107;
    private static final int LAYOUT_DIALOGDEALPLAYED = 108;
    private static final int LAYOUT_DIALOGDEALRECORDTYPE = 109;
    private static final int LAYOUT_DIALOGDEALSELLNOTICE1 = 110;
    private static final int LAYOUT_DIALOGGAMEUPDATEDETAIL = 111;
    private static final int LAYOUT_DIALOGGIFTCODE = 112;
    private static final int LAYOUT_DIALOGGMGAME = 113;
    private static final int LAYOUT_DIALOGGMITEMS = 114;
    private static final int LAYOUT_DIALOGGMLOG = 115;
    private static final int LAYOUT_DIALOGGMROLES = 116;
    private static final int LAYOUT_DIALOGHOME = 117;
    private static final int LAYOUT_DIALOGINVITE = 118;
    private static final int LAYOUT_DIALOGINVITEGAME = 119;
    private static final int LAYOUT_DIALOGINVITERECORD = 120;
    private static final int LAYOUT_DIALOGITEMADJUST = 121;
    private static final int LAYOUT_DIALOGLOGOUT = 122;
    private static final int LAYOUT_DIALOGLOTTERYRESULT = 123;
    private static final int LAYOUT_DIALOGNEWGAMETYPE = 124;
    private static final int LAYOUT_DIALOGNOVICEWELFARE = 125;
    private static final int LAYOUT_DIALOGPAY = 126;
    private static final int LAYOUT_DIALOGPIC = 127;
    private static final int LAYOUT_DIALOGPIN = 128;
    private static final int LAYOUT_DIALOGPRIVACY = 129;
    private static final int LAYOUT_DIALOGQIANDAOTASK = 130;
    private static final int LAYOUT_DIALOGRULE = 131;
    private static final int LAYOUT_DIALOGSELECTTRUMPET = 132;
    private static final int LAYOUT_DIALOGSHARE = 133;
    private static final int LAYOUT_DIALOGSQKRULE = 134;
    private static final int LAYOUT_DIALOGTIP = 135;
    private static final int LAYOUT_DIALOGTOPICRECORD = 137;
    private static final int LAYOUT_DIALOGTOPICRULE = 138;
    private static final int LAYOUT_DIALOGTOTHUNT = 136;
    private static final int LAYOUT_DIALOGUNABLEGAME = 139;
    private static final int LAYOUT_DIALOGUPDATE = 140;
    private static final int LAYOUT_DIALOGVIPCOUPONBUY = 141;
    private static final int LAYOUT_DIALOGVIPRIGHT2 = 142;
    private static final int LAYOUT_DIALOGVIPWEB = 143;
    private static final int LAYOUT_DIALOGWAIT = 144;
    private static final int LAYOUT_DIALOGWELFARE3GIFT = 145;
    private static final int LAYOUT_DIALOGWELFARE3ROLE = 146;
    private static final int LAYOUT_DIALOGWITHDRAW = 147;
    private static final int LAYOUT_DIALOGWITHDRAW2 = 148;
    private static final int LAYOUT_DIALOGYUNDEVICE = 149;
    private static final int LAYOUT_DIALOGYUNGAME = 150;
    private static final int LAYOUT_FRAGMENTBASE = 151;
    private static final int LAYOUT_FRAGMENTBBS = 152;
    private static final int LAYOUT_FRAGMENTBBS2 = 153;
    private static final int LAYOUT_FRAGMENTBBSINDEX = 154;
    private static final int LAYOUT_FRAGMENTCANCELLATION1 = 155;
    private static final int LAYOUT_FRAGMENTCANCELLATION2 = 156;
    private static final int LAYOUT_FRAGMENTCANCELLATION3 = 157;
    private static final int LAYOUT_FRAGMENTCANCELLATION4 = 158;
    private static final int LAYOUT_FRAGMENTDEAL = 159;
    private static final int LAYOUT_FRAGMENTDEALINDEX = 160;
    private static final int LAYOUT_FRAGMENTDEALRECORD = 161;
    private static final int LAYOUT_FRAGMENTDEALSELL = 162;
    private static final int LAYOUT_FRAGMENTGAME = 163;
    private static final int LAYOUT_FRAGMENTGAMECOMMENT = 164;
    private static final int LAYOUT_FRAGMENTGAMEINTRO = 165;
    private static final int LAYOUT_FRAGMENTGAMESERVER = 166;
    private static final int LAYOUT_FRAGMENTGAMETOOL = 167;
    private static final int LAYOUT_FRAGMENTHALL = 168;
    private static final int LAYOUT_FRAGMENTHALLGAME = 169;
    private static final int LAYOUT_FRAGMENTHOME = 170;
    private static final int LAYOUT_FRAGMENTHOMEGAME = 171;
    private static final int LAYOUT_FRAGMENTHOMELIST2 = 172;
    private static final int LAYOUT_FRAGMENTHOMENEW = 173;
    private static final int LAYOUT_FRAGMENTHOMESCHEDULE = 174;
    private static final int LAYOUT_FRAGMENTHOMESCHEDULE2 = 175;
    private static final int LAYOUT_FRAGMENTIMAGE = 176;
    private static final int LAYOUT_FRAGMENTITEMTRADE = 177;
    private static final int LAYOUT_FRAGMENTMAIN = 178;
    private static final int LAYOUT_FRAGMENTRV = 179;
    private static final int LAYOUT_FRAGMENTSANBAO648 = 180;
    private static final int LAYOUT_FRAGMENTSCHEDULE = 181;
    private static final int LAYOUT_FRAGMENTSUBSCRIBE = 182;
    private static final int LAYOUT_FRAGMENTSUBSCRIBE2 = 183;
    private static final int LAYOUT_FRAGMENTTOPICDETAIL = 184;
    private static final int LAYOUT_FRAGMENTUSER = 185;
    private static final int LAYOUT_FRAGMENTVIPRIGHT = 186;
    private static final int LAYOUT_FRAGMENTWEB = 187;
    private static final int LAYOUT_FRAGMENTWELFARE3 = 188;
    private static final int LAYOUT_FRAGMENTWELFARE4 = 189;
    private static final int LAYOUT_ITEMBBS = 190;
    private static final int LAYOUT_ITEMBBSCATE = 191;
    private static final int LAYOUT_ITEMBBSEDITCONTENT = 192;
    private static final int LAYOUT_ITEMBBSEDITTAG = 193;
    private static final int LAYOUT_ITEMBBSGAME = 194;
    private static final int LAYOUT_ITEMBBSMESSAGEINDEX = 195;
    private static final int LAYOUT_ITEMBBSMESSAGEOFFICIAL = 196;
    private static final int LAYOUT_ITEMBBSMESSAGEREPLY = 197;
    private static final int LAYOUT_ITEMBBSSEARCH = 198;
    private static final int LAYOUT_ITEMBBSSIGN = 199;
    private static final int LAYOUT_ITEMBBSSIGNWELFARE = 200;
    private static final int LAYOUT_ITEMBBSTOP = 201;
    private static final int LAYOUT_ITEMBILL = 202;
    private static final int LAYOUT_ITEMBOSSSERVER2 = 203;
    private static final int LAYOUT_ITEMBOSSSERVERGAME = 204;
    private static final int LAYOUT_ITEMCARDPRICE = 205;
    private static final int LAYOUT_ITEMCARDRECORD = 206;
    private static final int LAYOUT_ITEMCARDREWARDRECORD = 207;
    private static final int LAYOUT_ITEMCARDTITLE = 208;
    private static final int LAYOUT_ITEMCHAMPIONSHIPREWARD = 209;
    private static final int LAYOUT_ITEMCHAMPIONSHIPTASK = 210;
    private static final int LAYOUT_ITEMCOMMENTCATEGORY = 211;
    private static final int LAYOUT_ITEMCOMMENTCONTENT = 212;
    private static final int LAYOUT_ITEMCOMMENTTAG = 213;
    private static final int LAYOUT_ITEMDAILYCOUPON = 214;
    private static final int LAYOUT_ITEMDEAL = 215;
    private static final int LAYOUT_ITEMDEALDICKER = 216;
    private static final int LAYOUT_ITEMDEALFILTERTYPE = 217;
    private static final int LAYOUT_ITEMDEALFUN = 218;
    private static final int LAYOUT_ITEMDEALHOTGAME = 219;
    private static final int LAYOUT_ITEMDEALMONEYRECORD = 220;
    private static final int LAYOUT_ITEMDEALPIC = 221;
    private static final int LAYOUT_ITEMDEALPLAYED = 222;
    private static final int LAYOUT_ITEMDEALRECORDTYPE = 223;
    private static final int LAYOUT_ITEMDEALROLE = 224;
    private static final int LAYOUT_ITEMDEALSELL = 225;
    private static final int LAYOUT_ITEMDEALSELLCHILD = 226;
    private static final int LAYOUT_ITEMDIALOGHOME = 227;
    private static final int LAYOUT_ITEMDIALOGINVITEGAME = 228;
    private static final int LAYOUT_ITEMDIALOGYUNDEVICE = 229;
    private static final int LAYOUT_ITEMDOWNLOAD = 230;
    private static final int LAYOUT_ITEMFEEDBACKPIC = 231;
    private static final int LAYOUT_ITEMFUDAIINDEX = 232;
    private static final int LAYOUT_ITEMFUN = 233;
    private static final int LAYOUT_ITEMGAMEBANNERVIDEO = 234;
    private static final int LAYOUT_ITEMGAMECOMMENT = 235;
    private static final int LAYOUT_ITEMGAMECOMMENTSUB = 236;
    private static final int LAYOUT_ITEMGAMECOMMENTTOP = 237;
    private static final int LAYOUT_ITEMGAMECOUPON = 238;
    private static final int LAYOUT_ITEMGAMEDEAL = 239;
    private static final int LAYOUT_ITEMGAMEDETAILBANNER = 240;
    private static final int LAYOUT_ITEMGAMEDETAILCHAT = 241;
    private static final int LAYOUT_ITEMGAMEDETAILTAG = 242;
    private static final int LAYOUT_ITEMGAMEEVENT = 243;
    private static final int LAYOUT_ITEMGAMEINTRO1 = 244;
    private static final int LAYOUT_ITEMGAMEINTRO2 = 245;
    private static final int LAYOUT_ITEMGAMEINTRO3 = 246;
    private static final int LAYOUT_ITEMGAMEINTRO4 = 247;
    private static final int LAYOUT_ITEMGAMEINTROPIC = 248;
    private static final int LAYOUT_ITEMGAMESEARCH = 249;
    private static final int LAYOUT_ITEMGAMESERVICE = 250;
    private static final int LAYOUT_ITEMGAMETOOL = 251;
    private static final int LAYOUT_ITEMGAMETYPE = 252;
    private static final int LAYOUT_ITEMGAMETYPE2 = 253;
    private static final int LAYOUT_ITEMGAMEUPDATE = 254;
    private static final int LAYOUT_ITEMGAMEUPDATE2 = 255;
    private static final int LAYOUT_ITEMGAMEVIP = 256;
    private static final int LAYOUT_ITEMGIFT = 257;
    private static final int LAYOUT_ITEMGMGAME = 258;
    private static final int LAYOUT_ITEMGMITEM = 259;
    private static final int LAYOUT_ITEMGMLOG = 260;
    private static final int LAYOUT_ITEMGMROLE = 261;
    private static final int LAYOUT_ITEMGMTITLE = 262;
    private static final int LAYOUT_ITEMGROUPGAME = 263;
    private static final int LAYOUT_ITEMGROUPUSER = 264;
    private static final int LAYOUT_ITEMHALLFILTER = 265;
    private static final int LAYOUT_ITEMHALLGAME = 266;
    private static final int LAYOUT_ITEMHALLGAMETYPE = 267;
    private static final int LAYOUT_ITEMHOMEC1 = 268;
    private static final int LAYOUT_ITEMHOMEC2 = 269;
    private static final int LAYOUT_ITEMHOMEC3 = 270;
    private static final int LAYOUT_ITEMHOMEC4 = 271;
    private static final int LAYOUT_ITEMHOMECATE = 272;
    private static final int LAYOUT_ITEMHOMEGAME = 273;
    private static final int LAYOUT_ITEMHOMEGAME2 = 274;
    private static final int LAYOUT_ITEMHOMEGAMEHEAD = 275;
    private static final int LAYOUT_ITEMHOMEGROUP = 276;
    private static final int LAYOUT_ITEMHOMELARGE = 277;
    private static final int LAYOUT_ITEMHOMEMINI = 278;
    private static final int LAYOUT_ITEMHOMENEW = 279;
    private static final int LAYOUT_ITEMHOMEPIC = 280;
    private static final int LAYOUT_ITEMHOMERANK = 281;
    private static final int LAYOUT_ITEMHOMESCHEDULE = 282;
    private static final int LAYOUT_ITEMHOMESCHEDULE2 = 283;
    private static final int LAYOUT_ITEMHOMESCHEDULEHEAD = 284;
    private static final int LAYOUT_ITEMHOMETAB = 285;
    private static final int LAYOUT_ITEMHOMETYPE = 286;
    private static final int LAYOUT_ITEMHOMEVERTICAL = 287;
    private static final int LAYOUT_ITEMHOMEVIDEO1 = 288;
    private static final int LAYOUT_ITEMHOMEVIDEO2 = 289;
    private static final int LAYOUT_ITEMHOMEVIDEOGAME = 290;
    private static final int LAYOUT_ITEMINVITELIST = 291;
    private static final int LAYOUT_ITEMINVITERANK = 292;
    private static final int LAYOUT_ITEMINVITEWITHDRAWRECORD = 293;
    private static final int LAYOUT_ITEMINVITEWITHDREWRECORD = 294;
    private static final int LAYOUT_ITEMITEMSELLGAME = 295;
    private static final int LAYOUT_ITEMITEMSELLING = 297;
    private static final int LAYOUT_ITEMITEMSELLORDER = 296;
    private static final int LAYOUT_ITEMITEMTRADE = 298;
    private static final int LAYOUT_ITEMITEMTRADERECORD = 299;
    private static final int LAYOUT_ITEMITEMTRADESERVER = 300;
    private static final int LAYOUT_ITEMJFRECORD = 301;
    private static final int LAYOUT_ITEMLOTTERYFUN = 302;
    private static final int LAYOUT_ITEMLOTTERYGIFT = 303;
    private static final int LAYOUT_ITEMLOTTERYGIFTRESULT = 304;
    private static final int LAYOUT_ITEMLOTTERYRECORD = 305;
    private static final int LAYOUT_ITEMMAINTAB = 306;
    private static final int LAYOUT_ITEMMESSAGEDICKER = 307;
    private static final int LAYOUT_ITEMMESSAGEOFFICIAL = 308;
    private static final int LAYOUT_ITEMMESSAGEUSER = 309;
    private static final int LAYOUT_ITEMMYCOUPON = 310;
    private static final int LAYOUT_ITEMMYGAME = 311;
    private static final int LAYOUT_ITEMMYGIFT = 312;
    private static final int LAYOUT_ITEMMYVOUCHER = 313;
    private static final int LAYOUT_ITEMNOVICEGAMECOUPON = 314;
    private static final int LAYOUT_ITEMNOVICEGAMEGIFT = 315;
    private static final int LAYOUT_ITEMPERMISSIONLIST = 316;
    private static final int LAYOUT_ITEMPICSELECT = 317;
    private static final int LAYOUT_ITEMPOINTRECORD = 318;
    private static final int LAYOUT_ITEMPROMBLE = 319;
    private static final int LAYOUT_ITEMQIANDAO = 320;
    private static final int LAYOUT_ITEMQIANDAORECORD = 321;
    private static final int LAYOUT_ITEMQIANDAOTASK = 322;
    private static final int LAYOUT_ITEMQIANDAOUSER = 323;
    private static final int LAYOUT_ITEMRECYCLE = 324;
    private static final int LAYOUT_ITEMRECYCLEACCOUNT = 325;
    private static final int LAYOUT_ITEMRECYCLERECORD = 326;
    private static final int LAYOUT_ITEMREPORTPIC = 327;
    private static final int LAYOUT_ITEMSANBAO648 = 328;
    private static final int LAYOUT_ITEMSANBAO648COUPON = 329;
    private static final int LAYOUT_ITEMSANBAO648GIFT = 330;
    private static final int LAYOUT_ITEMSANBAOGAME = 331;
    private static final int LAYOUT_ITEMSANBAOGAME2 = 332;
    private static final int LAYOUT_ITEMSANBAOMESSAGE = 333;
    private static final int LAYOUT_ITEMSCHEDULETIME = 334;
    private static final int LAYOUT_ITEMSCHEDULETYPE = 335;
    private static final int LAYOUT_ITEMSEARCHHISTORY = 336;
    private static final int LAYOUT_ITEMSEARCHHOT = 337;
    private static final int LAYOUT_ITEMSEARCHTYPE = 338;
    private static final int LAYOUT_ITEMSELECTTRUMPET = 339;
    private static final int LAYOUT_ITEMSERVICE = 340;
    private static final int LAYOUT_ITEMSERVICEPROBLEM = 341;
    private static final int LAYOUT_ITEMTASK = 342;
    private static final int LAYOUT_ITEMTASKDAILY = 343;
    private static final int LAYOUT_ITEMTASKMONTHLY = 344;
    private static final int LAYOUT_ITEMTOPICBANNER = 345;
    private static final int LAYOUT_ITEMTOPICLOTTERY = 346;
    private static final int LAYOUT_ITEMTOPICRECORD = 347;
    private static final int LAYOUT_ITEMTOPICTASK = 348;
    private static final int LAYOUT_ITEMTRUMPET1 = 349;
    private static final int LAYOUT_ITEMTRUMPET2 = 350;
    private static final int LAYOUT_ITEMUNABLEGAME = 351;
    private static final int LAYOUT_ITEMVIPCOUPON = 352;
    private static final int LAYOUT_ITEMVIPCOUPONLEVEL = 353;
    private static final int LAYOUT_ITEMVIPFLB = 354;
    private static final int LAYOUT_ITEMVIPFUN = 355;
    private static final int LAYOUT_ITEMVIPGIFT = 356;
    private static final int LAYOUT_ITEMVIPGIFTSMALL = 357;
    private static final int LAYOUT_ITEMVIPRIGHT = 358;
    private static final int LAYOUT_ITEMWELFARE31 = 359;
    private static final int LAYOUT_ITEMWELFARE3GAME = 360;
    private static final int LAYOUT_ITEMWELFARE3GAMESMALL = 361;
    private static final int LAYOUT_ITEMWELFARE3GIFT = 362;
    private static final int LAYOUT_ITEMWELFARE3GIFT2 = 363;
    private static final int LAYOUT_ITEMWELFARE3ROLE = 364;
    private static final int LAYOUT_ITEMWELFARECARD = 365;
    private static final int LAYOUT_ITEMWELFAREEVENTREPLY = 366;
    private static final int LAYOUT_ITEMWELFAREQIANDAO = 367;
    private static final int LAYOUT_ITEMWELFARETASK = 368;
    private static final int LAYOUT_ITEMWELFARETASK2 = 369;
    private static final int LAYOUT_ITEMWELFARETASK3 = 370;
    private static final int LAYOUT_ITEMWITHDREWRECORD = 371;
    private static final int LAYOUT_ITEMYUNBLOCK = 372;
    private static final int LAYOUT_ITEMYUNGAME = 373;
    private static final int LAYOUT_ITEMYUNPOPDEVICE = 374;
    private static final int LAYOUT_ITEMYUNPRICE = 375;
    private static final int LAYOUT_ITEMYUNTIP = 376;
    private static final int LAYOUT_LAYOUTBANNERBBS = 377;
    private static final int LAYOUT_LAYOUTBANNERHOME = 378;
    private static final int LAYOUT_LAYOUTDEALALL = 379;
    private static final int LAYOUT_LAYOUTDISCOUNT = 380;
    private static final int LAYOUT_LAYOUTGAMEDETAILTAB = 381;
    private static final int LAYOUT_LAYOUTGAMEICON = 382;
    private static final int LAYOUT_LAYOUTGAMENAME = 383;
    private static final int LAYOUT_LAYOUTGAMETAG = 384;
    private static final int LAYOUT_LAYOUTGAMETAG2 = 385;
    private static final int LAYOUT_LAYOUTGAMETAG3 = 386;
    private static final int LAYOUT_LAYOUTTABTEXT = 387;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(LAYOUT_LAYOUTTABTEXT);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_address, 1);
        sparseIntArray.put(R.layout.activity_alipay_bind, 2);
        sparseIntArray.put(R.layout.activity_auth, 3);
        sparseIntArray.put(R.layout.activity_baofu, 4);
        sparseIntArray.put(R.layout.activity_bbs_detail, 5);
        sparseIntArray.put(R.layout.activity_bbs_detail2, 6);
        sparseIntArray.put(R.layout.activity_bbs_edit, 7);
        sparseIntArray.put(R.layout.activity_bbs_message, 8);
        sparseIntArray.put(R.layout.activity_bbs_search, 9);
        sparseIntArray.put(R.layout.activity_boss_server, 10);
        sparseIntArray.put(R.layout.activity_cancellation, 11);
        sparseIntArray.put(R.layout.activity_card, 12);
        sparseIntArray.put(R.layout.activity_championship, 13);
        sparseIntArray.put(R.layout.activity_change_password, 14);
        sparseIntArray.put(R.layout.activity_comment_detail, 15);
        sparseIntArray.put(R.layout.activity_comment_edit, 16);
        sparseIntArray.put(R.layout.activity_daily_coupon, 17);
        sparseIntArray.put(R.layout.activity_daily_task, 18);
        sparseIntArray.put(R.layout.activity_deal_detail, 19);
        sparseIntArray.put(R.layout.activity_deal_list, 20);
        sparseIntArray.put(R.layout.activity_deal_record, 21);
        sparseIntArray.put(R.layout.activity_deal_sell, 22);
        sparseIntArray.put(R.layout.activity_deal_sell_info, 23);
        sparseIntArray.put(R.layout.activity_develop, 24);
        sparseIntArray.put(R.layout.activity_download, 25);
        sparseIntArray.put(R.layout.activity_empty, 26);
        sparseIntArray.put(R.layout.activity_event_detail, 27);
        sparseIntArray.put(R.layout.activity_feedback, 28);
        sparseIntArray.put(R.layout.activity_fragment, 29);
        sparseIntArray.put(R.layout.activity_fudai_index, 30);
        sparseIntArray.put(R.layout.activity_game_coupon, 31);
        sparseIntArray.put(R.layout.activity_game_detail, 32);
        sparseIntArray.put(R.layout.activity_game_report, 33);
        sparseIntArray.put(R.layout.activity_game_report2, 34);
        sparseIntArray.put(R.layout.activity_gift_detail, 35);
        sparseIntArray.put(R.layout.activity_gm, 36);
        sparseIntArray.put(R.layout.activity_gm_pay, 37);
        sparseIntArray.put(R.layout.activity_gm_trans, 38);
        sparseIntArray.put(R.layout.activity_gm_trans_pay, 39);
        sparseIntArray.put(R.layout.activity_image, 40);
        sparseIntArray.put(R.layout.activity_info, 41);
        sparseIntArray.put(R.layout.activity_invite3, 42);
        sparseIntArray.put(R.layout.activity_invite_withdrew, 43);
        sparseIntArray.put(R.layout.activity_item_sell, 44);
        sparseIntArray.put(R.layout.activity_item_sell_game, 45);
        sparseIntArray.put(R.layout.activity_item_sell_record, 46);
        sparseIntArray.put(R.layout.activity_item_trade_detail, 47);
        sparseIntArray.put(R.layout.activity_jf_record, 48);
        sparseIntArray.put(R.layout.activity_login, 49);
        sparseIntArray.put(R.layout.activity_lottery, 50);
        sparseIntArray.put(R.layout.activity_lottery_record, 51);
        sparseIntArray.put(R.layout.activity_main, 52);
        sparseIntArray.put(R.layout.activity_message, 53);
        sparseIntArray.put(R.layout.activity_month_card, 54);
        sparseIntArray.put(R.layout.activity_monthly_task, 55);
        sparseIntArray.put(R.layout.activity_my_money, 56);
        sparseIntArray.put(R.layout.activity_my_welfare, 57);
        sparseIntArray.put(R.layout.activity_novice_task, 58);
        sparseIntArray.put(R.layout.activity_novice_welfare, 59);
        sparseIntArray.put(R.layout.activity_phone, 60);
        sparseIntArray.put(R.layout.activity_pin, 61);
        sparseIntArray.put(R.layout.activity_ptb, 62);
        sparseIntArray.put(R.layout.activity_ptb_detail, 63);
        sparseIntArray.put(R.layout.activity_qiandao, 64);
        sparseIntArray.put(R.layout.activity_qiandao_record, 65);
        sparseIntArray.put(R.layout.activity_quick_login, 66);
        sparseIntArray.put(R.layout.activity_record_detail, 67);
        sparseIntArray.put(R.layout.activity_recycle, 68);
        sparseIntArray.put(R.layout.activity_register, 69);
        sparseIntArray.put(R.layout.activity_rv, 70);
        sparseIntArray.put(R.layout.activity_rv_tab, 71);
        sparseIntArray.put(R.layout.activity_safe, 72);
        sparseIntArray.put(R.layout.activity_sanbao, 73);
        sparseIntArray.put(R.layout.activity_search, 74);
        sparseIntArray.put(R.layout.activity_service, 75);
        sparseIntArray.put(R.layout.activity_service_detail, 76);
        sparseIntArray.put(R.layout.activity_setting, 77);
        sparseIntArray.put(R.layout.activity_splash, 78);
        sparseIntArray.put(R.layout.activity_subscribe, 79);
        sparseIntArray.put(R.layout.activity_task_hall, 80);
        sparseIntArray.put(R.layout.activity_topic_detail, 81);
        sparseIntArray.put(R.layout.activity_trumpet, 82);
        sparseIntArray.put(R.layout.activity_user, 83);
        sparseIntArray.put(R.layout.activity_video, 84);
        sparseIntArray.put(R.layout.activity_vip, 85);
        sparseIntArray.put(R.layout.activity_vip_coupon, 86);
        sparseIntArray.put(R.layout.activity_web, 87);
        sparseIntArray.put(R.layout.activity_web2, 88);
        sparseIntArray.put(R.layout.activity_web_pay, 89);
        sparseIntArray.put(R.layout.activity_welfare_event_detail, 90);
        sparseIntArray.put(R.layout.activity_withdrew, 91);
        sparseIntArray.put(R.layout.activity_yun_buy, 92);
        sparseIntArray.put(R.layout.activity_yun_play, 93);
        sparseIntArray.put(R.layout.dialog_bbs_game, 94);
        sparseIntArray.put(R.layout.dialog_bbs_reply, 95);
        sparseIntArray.put(R.layout.dialog_bbs_sign, 96);
        sparseIntArray.put(R.layout.dialog_boss_server, 97);
        sparseIntArray.put(R.layout.dialog_bottom_tip, 98);
        sparseIntArray.put(R.layout.dialog_bottom_tip2, 99);
        sparseIntArray.put(R.layout.dialog_comment, 100);
        sparseIntArray.put(R.layout.dialog_confirm, 101);
        sparseIntArray.put(R.layout.dialog_daily_coupon_receive, 102);
        sparseIntArray.put(R.layout.dialog_deal_buy_notice, 103);
        sparseIntArray.put(R.layout.dialog_deal_detail, 104);
        sparseIntArray.put(R.layout.dialog_deal_dicker, 105);
        sparseIntArray.put(R.layout.dialog_deal_filter, 106);
        sparseIntArray.put(R.layout.dialog_deal_offset, 107);
        sparseIntArray.put(R.layout.dialog_deal_played, 108);
        sparseIntArray.put(R.layout.dialog_deal_record_type, 109);
        sparseIntArray.put(R.layout.dialog_deal_sell_notice1, 110);
        sparseIntArray.put(R.layout.dialog_game_update_detail, 111);
        sparseIntArray.put(R.layout.dialog_gift_code, 112);
        sparseIntArray.put(R.layout.dialog_gm_game, 113);
        sparseIntArray.put(R.layout.dialog_gm_items, 114);
        sparseIntArray.put(R.layout.dialog_gm_log, 115);
        sparseIntArray.put(R.layout.dialog_gm_roles, 116);
        sparseIntArray.put(R.layout.dialog_home, 117);
        sparseIntArray.put(R.layout.dialog_invite, 118);
        sparseIntArray.put(R.layout.dialog_invite_game, 119);
        sparseIntArray.put(R.layout.dialog_invite_record, 120);
        sparseIntArray.put(R.layout.dialog_item_adjust, 121);
        sparseIntArray.put(R.layout.dialog_logout, 122);
        sparseIntArray.put(R.layout.dialog_lottery_result, 123);
        sparseIntArray.put(R.layout.dialog_new_game_type, 124);
        sparseIntArray.put(R.layout.dialog_novice_welfare, 125);
        sparseIntArray.put(R.layout.dialog_pay, 126);
        sparseIntArray.put(R.layout.dialog_pic, 127);
        sparseIntArray.put(R.layout.dialog_pin, 128);
        sparseIntArray.put(R.layout.dialog_privacy, 129);
        sparseIntArray.put(R.layout.dialog_qiandao_task, 130);
        sparseIntArray.put(R.layout.dialog_rule, 131);
        sparseIntArray.put(R.layout.dialog_select_trumpet, 132);
        sparseIntArray.put(R.layout.dialog_share, 133);
        sparseIntArray.put(R.layout.dialog_sqk_rule, 134);
        sparseIntArray.put(R.layout.dialog_tip, 135);
        sparseIntArray.put(R.layout.dialog_to_thunt, 136);
        sparseIntArray.put(R.layout.dialog_topic_record, 137);
        sparseIntArray.put(R.layout.dialog_topic_rule, 138);
        sparseIntArray.put(R.layout.dialog_unable_game, 139);
        sparseIntArray.put(R.layout.dialog_update, 140);
        sparseIntArray.put(R.layout.dialog_vip_coupon_buy, 141);
        sparseIntArray.put(R.layout.dialog_vip_right2, 142);
        sparseIntArray.put(R.layout.dialog_vip_web, 143);
        sparseIntArray.put(R.layout.dialog_wait, 144);
        sparseIntArray.put(R.layout.dialog_welfare3_gift, 145);
        sparseIntArray.put(R.layout.dialog_welfare3_role, 146);
        sparseIntArray.put(R.layout.dialog_withdraw, 147);
        sparseIntArray.put(R.layout.dialog_withdraw2, 148);
        sparseIntArray.put(R.layout.dialog_yun_device, 149);
        sparseIntArray.put(R.layout.dialog_yun_game, 150);
        sparseIntArray.put(R.layout.fragment_base, LAYOUT_FRAGMENTBASE);
        sparseIntArray.put(R.layout.fragment_bbs, LAYOUT_FRAGMENTBBS);
        sparseIntArray.put(R.layout.fragment_bbs2, LAYOUT_FRAGMENTBBS2);
        sparseIntArray.put(R.layout.fragment_bbs_index, LAYOUT_FRAGMENTBBSINDEX);
        sparseIntArray.put(R.layout.fragment_cancellation1, LAYOUT_FRAGMENTCANCELLATION1);
        sparseIntArray.put(R.layout.fragment_cancellation2, LAYOUT_FRAGMENTCANCELLATION2);
        sparseIntArray.put(R.layout.fragment_cancellation3, LAYOUT_FRAGMENTCANCELLATION3);
        sparseIntArray.put(R.layout.fragment_cancellation4, LAYOUT_FRAGMENTCANCELLATION4);
        sparseIntArray.put(R.layout.fragment_deal, LAYOUT_FRAGMENTDEAL);
        sparseIntArray.put(R.layout.fragment_deal_index, 160);
        sparseIntArray.put(R.layout.fragment_deal_record, 161);
        sparseIntArray.put(R.layout.fragment_deal_sell, 162);
        sparseIntArray.put(R.layout.fragment_game, 163);
        sparseIntArray.put(R.layout.fragment_game_comment, 164);
        sparseIntArray.put(R.layout.fragment_game_intro, 165);
        sparseIntArray.put(R.layout.fragment_game_server, 166);
        sparseIntArray.put(R.layout.fragment_game_tool, 167);
        sparseIntArray.put(R.layout.fragment_hall, 168);
        sparseIntArray.put(R.layout.fragment_hall_game, 169);
        sparseIntArray.put(R.layout.fragment_home, 170);
        sparseIntArray.put(R.layout.fragment_home_game, 171);
        sparseIntArray.put(R.layout.fragment_home_list2, 172);
        sparseIntArray.put(R.layout.fragment_home_new, 173);
        sparseIntArray.put(R.layout.fragment_home_schedule, 174);
        sparseIntArray.put(R.layout.fragment_home_schedule2, 175);
        sparseIntArray.put(R.layout.fragment_image, 176);
        sparseIntArray.put(R.layout.fragment_item_trade, 177);
        sparseIntArray.put(R.layout.fragment_main, 178);
        sparseIntArray.put(R.layout.fragment_rv, 179);
        sparseIntArray.put(R.layout.fragment_sanbao648, 180);
        sparseIntArray.put(R.layout.fragment_schedule, 181);
        sparseIntArray.put(R.layout.fragment_subscribe, 182);
        sparseIntArray.put(R.layout.fragment_subscribe2, 183);
        sparseIntArray.put(R.layout.fragment_topic_detail, LAYOUT_FRAGMENTTOPICDETAIL);
        sparseIntArray.put(R.layout.fragment_user, LAYOUT_FRAGMENTUSER);
        sparseIntArray.put(R.layout.fragment_vip_right, 186);
        sparseIntArray.put(R.layout.fragment_web, 187);
        sparseIntArray.put(R.layout.fragment_welfare3, 188);
        sparseIntArray.put(R.layout.fragment_welfare4, 189);
        sparseIntArray.put(R.layout.item_bbs, 190);
        sparseIntArray.put(R.layout.item_bbs_cate, 191);
        sparseIntArray.put(R.layout.item_bbs_edit_content, 192);
        sparseIntArray.put(R.layout.item_bbs_edit_tag, LAYOUT_ITEMBBSEDITTAG);
        sparseIntArray.put(R.layout.item_bbs_game, LAYOUT_ITEMBBSGAME);
        sparseIntArray.put(R.layout.item_bbs_message_index, 195);
        sparseIntArray.put(R.layout.item_bbs_message_official, 196);
        sparseIntArray.put(R.layout.item_bbs_message_reply, 197);
        sparseIntArray.put(R.layout.item_bbs_search, 198);
        sparseIntArray.put(R.layout.item_bbs_sign, 199);
        sparseIntArray.put(R.layout.item_bbs_sign_welfare, 200);
        sparseIntArray.put(R.layout.item_bbs_top, 201);
        sparseIntArray.put(R.layout.item_bill, 202);
        sparseIntArray.put(R.layout.item_boss_server2, 203);
        sparseIntArray.put(R.layout.item_boss_server_game, 204);
        sparseIntArray.put(R.layout.item_card_price, 205);
        sparseIntArray.put(R.layout.item_card_record, 206);
        sparseIntArray.put(R.layout.item_card_reward_record, 207);
        sparseIntArray.put(R.layout.item_card_title, 208);
        sparseIntArray.put(R.layout.item_championship_reward, 209);
        sparseIntArray.put(R.layout.item_championship_task, 210);
        sparseIntArray.put(R.layout.item_comment_category, 211);
        sparseIntArray.put(R.layout.item_comment_content, 212);
        sparseIntArray.put(R.layout.item_comment_tag, 213);
        sparseIntArray.put(R.layout.item_daily_coupon, 214);
        sparseIntArray.put(R.layout.item_deal, 215);
        sparseIntArray.put(R.layout.item_deal_dicker, 216);
        sparseIntArray.put(R.layout.item_deal_filter_type, 217);
        sparseIntArray.put(R.layout.item_deal_fun, 218);
        sparseIntArray.put(R.layout.item_deal_hot_game, 219);
        sparseIntArray.put(R.layout.item_deal_money_record, 220);
        sparseIntArray.put(R.layout.item_deal_pic, 221);
        sparseIntArray.put(R.layout.item_deal_played, 222);
        sparseIntArray.put(R.layout.item_deal_record_type, 223);
        sparseIntArray.put(R.layout.item_deal_role, LAYOUT_ITEMDEALROLE);
        sparseIntArray.put(R.layout.item_deal_sell, 225);
        sparseIntArray.put(R.layout.item_deal_sell_child, 226);
        sparseIntArray.put(R.layout.item_dialog_home, 227);
        sparseIntArray.put(R.layout.item_dialog_invite_game, 228);
        sparseIntArray.put(R.layout.item_dialog_yun_device, 229);
        sparseIntArray.put(R.layout.item_download, 230);
        sparseIntArray.put(R.layout.item_feedback_pic, 231);
        sparseIntArray.put(R.layout.item_fudai_index, LAYOUT_ITEMFUDAIINDEX);
        sparseIntArray.put(R.layout.item_fun, 233);
        sparseIntArray.put(R.layout.item_game_banner_video, 234);
        sparseIntArray.put(R.layout.item_game_comment, 235);
        sparseIntArray.put(R.layout.item_game_comment_sub, 236);
        sparseIntArray.put(R.layout.item_game_comment_top, 237);
        sparseIntArray.put(R.layout.item_game_coupon, 238);
        sparseIntArray.put(R.layout.item_game_deal, 239);
        sparseIntArray.put(R.layout.item_game_detail_banner, 240);
        sparseIntArray.put(R.layout.item_game_detail_chat, 241);
        sparseIntArray.put(R.layout.item_game_detail_tag, 242);
        sparseIntArray.put(R.layout.item_game_event, 243);
        sparseIntArray.put(R.layout.item_game_intro1, 244);
        sparseIntArray.put(R.layout.item_game_intro2, 245);
        sparseIntArray.put(R.layout.item_game_intro3, 246);
        sparseIntArray.put(R.layout.item_game_intro4, 247);
        sparseIntArray.put(R.layout.item_game_intro_pic, 248);
        sparseIntArray.put(R.layout.item_game_search, 249);
        sparseIntArray.put(R.layout.item_game_service, 250);
        sparseIntArray.put(R.layout.item_game_tool, 251);
        sparseIntArray.put(R.layout.item_game_type, 252);
        sparseIntArray.put(R.layout.item_game_type2, 253);
        sparseIntArray.put(R.layout.item_game_update, 254);
        sparseIntArray.put(R.layout.item_game_update2, 255);
        sparseIntArray.put(R.layout.item_game_vip, 256);
        sparseIntArray.put(R.layout.item_gift, 257);
        sparseIntArray.put(R.layout.item_gm_game, LAYOUT_ITEMGMGAME);
        sparseIntArray.put(R.layout.item_gm_item, LAYOUT_ITEMGMITEM);
        sparseIntArray.put(R.layout.item_gm_log, LAYOUT_ITEMGMLOG);
        sparseIntArray.put(R.layout.item_gm_role, LAYOUT_ITEMGMROLE);
        sparseIntArray.put(R.layout.item_gm_title, LAYOUT_ITEMGMTITLE);
        sparseIntArray.put(R.layout.item_group_game, 263);
        sparseIntArray.put(R.layout.item_group_user, LAYOUT_ITEMGROUPUSER);
        sparseIntArray.put(R.layout.item_hall_filter, LAYOUT_ITEMHALLFILTER);
        sparseIntArray.put(R.layout.item_hall_game, LAYOUT_ITEMHALLGAME);
        sparseIntArray.put(R.layout.item_hall_game_type, LAYOUT_ITEMHALLGAMETYPE);
        sparseIntArray.put(R.layout.item_home_c1, LAYOUT_ITEMHOMEC1);
        sparseIntArray.put(R.layout.item_home_c2, LAYOUT_ITEMHOMEC2);
        sparseIntArray.put(R.layout.item_home_c3, 270);
        sparseIntArray.put(R.layout.item_home_c4, LAYOUT_ITEMHOMEC4);
        sparseIntArray.put(R.layout.item_home_cate, LAYOUT_ITEMHOMECATE);
        sparseIntArray.put(R.layout.item_home_game, LAYOUT_ITEMHOMEGAME);
        sparseIntArray.put(R.layout.item_home_game2, LAYOUT_ITEMHOMEGAME2);
        sparseIntArray.put(R.layout.item_home_game_head, LAYOUT_ITEMHOMEGAMEHEAD);
        sparseIntArray.put(R.layout.item_home_group, LAYOUT_ITEMHOMEGROUP);
        sparseIntArray.put(R.layout.item_home_large, LAYOUT_ITEMHOMELARGE);
        sparseIntArray.put(R.layout.item_home_mini, LAYOUT_ITEMHOMEMINI);
        sparseIntArray.put(R.layout.item_home_new, LAYOUT_ITEMHOMENEW);
        sparseIntArray.put(R.layout.item_home_pic, LAYOUT_ITEMHOMEPIC);
        sparseIntArray.put(R.layout.item_home_rank, LAYOUT_ITEMHOMERANK);
        sparseIntArray.put(R.layout.item_home_schedule, LAYOUT_ITEMHOMESCHEDULE);
        sparseIntArray.put(R.layout.item_home_schedule2, LAYOUT_ITEMHOMESCHEDULE2);
        sparseIntArray.put(R.layout.item_home_schedule_head, LAYOUT_ITEMHOMESCHEDULEHEAD);
        sparseIntArray.put(R.layout.item_home_tab, LAYOUT_ITEMHOMETAB);
        sparseIntArray.put(R.layout.item_home_type, LAYOUT_ITEMHOMETYPE);
        sparseIntArray.put(R.layout.item_home_vertical, LAYOUT_ITEMHOMEVERTICAL);
        sparseIntArray.put(R.layout.item_home_video1, LAYOUT_ITEMHOMEVIDEO1);
        sparseIntArray.put(R.layout.item_home_video2, LAYOUT_ITEMHOMEVIDEO2);
        sparseIntArray.put(R.layout.item_home_video_game, LAYOUT_ITEMHOMEVIDEOGAME);
        sparseIntArray.put(R.layout.item_invite_list, LAYOUT_ITEMINVITELIST);
        sparseIntArray.put(R.layout.item_invite_rank, LAYOUT_ITEMINVITERANK);
        sparseIntArray.put(R.layout.item_invite_withdraw_record, LAYOUT_ITEMINVITEWITHDRAWRECORD);
        sparseIntArray.put(R.layout.item_invite_withdrew_record, LAYOUT_ITEMINVITEWITHDREWRECORD);
        sparseIntArray.put(R.layout.item_item_sell_game, LAYOUT_ITEMITEMSELLGAME);
        sparseIntArray.put(R.layout.item_item_sell_order, LAYOUT_ITEMITEMSELLORDER);
        sparseIntArray.put(R.layout.item_item_selling, LAYOUT_ITEMITEMSELLING);
        sparseIntArray.put(R.layout.item_item_trade, LAYOUT_ITEMITEMTRADE);
        sparseIntArray.put(R.layout.item_item_trade_record, LAYOUT_ITEMITEMTRADERECORD);
        sparseIntArray.put(R.layout.item_item_trade_server, 300);
        sparseIntArray.put(R.layout.item_jf_record, LAYOUT_ITEMJFRECORD);
        sparseIntArray.put(R.layout.item_lottery_fun, LAYOUT_ITEMLOTTERYFUN);
        sparseIntArray.put(R.layout.item_lottery_gift, LAYOUT_ITEMLOTTERYGIFT);
        sparseIntArray.put(R.layout.item_lottery_gift_result, LAYOUT_ITEMLOTTERYGIFTRESULT);
        sparseIntArray.put(R.layout.item_lottery_record, LAYOUT_ITEMLOTTERYRECORD);
        sparseIntArray.put(R.layout.item_main_tab, LAYOUT_ITEMMAINTAB);
        sparseIntArray.put(R.layout.item_message_dicker, 307);
        sparseIntArray.put(R.layout.item_message_official, 308);
        sparseIntArray.put(R.layout.item_message_user, LAYOUT_ITEMMESSAGEUSER);
        sparseIntArray.put(R.layout.item_my_coupon, LAYOUT_ITEMMYCOUPON);
        sparseIntArray.put(R.layout.item_my_game, LAYOUT_ITEMMYGAME);
        sparseIntArray.put(R.layout.item_my_gift, LAYOUT_ITEMMYGIFT);
        sparseIntArray.put(R.layout.item_my_voucher, LAYOUT_ITEMMYVOUCHER);
        sparseIntArray.put(R.layout.item_novice_game_coupon, LAYOUT_ITEMNOVICEGAMECOUPON);
        sparseIntArray.put(R.layout.item_novice_game_gift, LAYOUT_ITEMNOVICEGAMEGIFT);
        sparseIntArray.put(R.layout.item_permission_list, LAYOUT_ITEMPERMISSIONLIST);
        sparseIntArray.put(R.layout.item_pic_select, LAYOUT_ITEMPICSELECT);
        sparseIntArray.put(R.layout.item_point_record, LAYOUT_ITEMPOINTRECORD);
        sparseIntArray.put(R.layout.item_promble, LAYOUT_ITEMPROMBLE);
        sparseIntArray.put(R.layout.item_qiandao, LAYOUT_ITEMQIANDAO);
        sparseIntArray.put(R.layout.item_qiandao_record, LAYOUT_ITEMQIANDAORECORD);
        sparseIntArray.put(R.layout.item_qiandao_task, LAYOUT_ITEMQIANDAOTASK);
        sparseIntArray.put(R.layout.item_qiandao_user, LAYOUT_ITEMQIANDAOUSER);
        sparseIntArray.put(R.layout.item_recycle, LAYOUT_ITEMRECYCLE);
        sparseIntArray.put(R.layout.item_recycle_account, LAYOUT_ITEMRECYCLEACCOUNT);
        sparseIntArray.put(R.layout.item_recycle_record, LAYOUT_ITEMRECYCLERECORD);
        sparseIntArray.put(R.layout.item_report_pic, LAYOUT_ITEMREPORTPIC);
        sparseIntArray.put(R.layout.item_sanbao_648, LAYOUT_ITEMSANBAO648);
        sparseIntArray.put(R.layout.item_sanbao_648_coupon, LAYOUT_ITEMSANBAO648COUPON);
        sparseIntArray.put(R.layout.item_sanbao_648_gift, LAYOUT_ITEMSANBAO648GIFT);
        sparseIntArray.put(R.layout.item_sanbao_game, LAYOUT_ITEMSANBAOGAME);
        sparseIntArray.put(R.layout.item_sanbao_game2, LAYOUT_ITEMSANBAOGAME2);
        sparseIntArray.put(R.layout.item_sanbao_message, LAYOUT_ITEMSANBAOMESSAGE);
        sparseIntArray.put(R.layout.item_schedule_time, LAYOUT_ITEMSCHEDULETIME);
        sparseIntArray.put(R.layout.item_schedule_type, LAYOUT_ITEMSCHEDULETYPE);
        sparseIntArray.put(R.layout.item_search_history, LAYOUT_ITEMSEARCHHISTORY);
        sparseIntArray.put(R.layout.item_search_hot, LAYOUT_ITEMSEARCHHOT);
        sparseIntArray.put(R.layout.item_search_type, LAYOUT_ITEMSEARCHTYPE);
        sparseIntArray.put(R.layout.item_select_trumpet, LAYOUT_ITEMSELECTTRUMPET);
        sparseIntArray.put(R.layout.item_service, LAYOUT_ITEMSERVICE);
        sparseIntArray.put(R.layout.item_service_problem, LAYOUT_ITEMSERVICEPROBLEM);
        sparseIntArray.put(R.layout.item_task, LAYOUT_ITEMTASK);
        sparseIntArray.put(R.layout.item_task_daily, LAYOUT_ITEMTASKDAILY);
        sparseIntArray.put(R.layout.item_task_monthly, LAYOUT_ITEMTASKMONTHLY);
        sparseIntArray.put(R.layout.item_topic_banner, LAYOUT_ITEMTOPICBANNER);
        sparseIntArray.put(R.layout.item_topic_lottery, LAYOUT_ITEMTOPICLOTTERY);
        sparseIntArray.put(R.layout.item_topic_record, LAYOUT_ITEMTOPICRECORD);
        sparseIntArray.put(R.layout.item_topic_task, LAYOUT_ITEMTOPICTASK);
        sparseIntArray.put(R.layout.item_trumpet1, LAYOUT_ITEMTRUMPET1);
        sparseIntArray.put(R.layout.item_trumpet2, LAYOUT_ITEMTRUMPET2);
        sparseIntArray.put(R.layout.item_unable_game, LAYOUT_ITEMUNABLEGAME);
        sparseIntArray.put(R.layout.item_vip_coupon, LAYOUT_ITEMVIPCOUPON);
        sparseIntArray.put(R.layout.item_vip_coupon_level, LAYOUT_ITEMVIPCOUPONLEVEL);
        sparseIntArray.put(R.layout.item_vip_flb, LAYOUT_ITEMVIPFLB);
        sparseIntArray.put(R.layout.item_vip_fun, LAYOUT_ITEMVIPFUN);
        sparseIntArray.put(R.layout.item_vip_gift, LAYOUT_ITEMVIPGIFT);
        sparseIntArray.put(R.layout.item_vip_gift_small, LAYOUT_ITEMVIPGIFTSMALL);
        sparseIntArray.put(R.layout.item_vip_right, LAYOUT_ITEMVIPRIGHT);
        sparseIntArray.put(R.layout.item_welfare3_1, LAYOUT_ITEMWELFARE31);
        sparseIntArray.put(R.layout.item_welfare3_game, LAYOUT_ITEMWELFARE3GAME);
        sparseIntArray.put(R.layout.item_welfare3_game_small, LAYOUT_ITEMWELFARE3GAMESMALL);
        sparseIntArray.put(R.layout.item_welfare3_gift, LAYOUT_ITEMWELFARE3GIFT);
        sparseIntArray.put(R.layout.item_welfare3_gift2, LAYOUT_ITEMWELFARE3GIFT2);
        sparseIntArray.put(R.layout.item_welfare3_role, LAYOUT_ITEMWELFARE3ROLE);
        sparseIntArray.put(R.layout.item_welfare_card, LAYOUT_ITEMWELFARECARD);
        sparseIntArray.put(R.layout.item_welfare_event_reply, LAYOUT_ITEMWELFAREEVENTREPLY);
        sparseIntArray.put(R.layout.item_welfare_qiandao, LAYOUT_ITEMWELFAREQIANDAO);
        sparseIntArray.put(R.layout.item_welfare_task, LAYOUT_ITEMWELFARETASK);
        sparseIntArray.put(R.layout.item_welfare_task2, LAYOUT_ITEMWELFARETASK2);
        sparseIntArray.put(R.layout.item_welfare_task3, LAYOUT_ITEMWELFARETASK3);
        sparseIntArray.put(R.layout.item_withdrew_record, LAYOUT_ITEMWITHDREWRECORD);
        sparseIntArray.put(R.layout.item_yun_block, LAYOUT_ITEMYUNBLOCK);
        sparseIntArray.put(R.layout.item_yun_game, LAYOUT_ITEMYUNGAME);
        sparseIntArray.put(R.layout.item_yun_pop_device, LAYOUT_ITEMYUNPOPDEVICE);
        sparseIntArray.put(R.layout.item_yun_price, LAYOUT_ITEMYUNPRICE);
        sparseIntArray.put(R.layout.item_yun_tip, LAYOUT_ITEMYUNTIP);
        sparseIntArray.put(R.layout.layout_banner_bbs, LAYOUT_LAYOUTBANNERBBS);
        sparseIntArray.put(R.layout.layout_banner_home, LAYOUT_LAYOUTBANNERHOME);
        sparseIntArray.put(R.layout.layout_deal_all, LAYOUT_LAYOUTDEALALL);
        sparseIntArray.put(R.layout.layout_discount, LAYOUT_LAYOUTDISCOUNT);
        sparseIntArray.put(R.layout.layout_game_detail_tab, LAYOUT_LAYOUTGAMEDETAILTAB);
        sparseIntArray.put(R.layout.layout_game_icon, LAYOUT_LAYOUTGAMEICON);
        sparseIntArray.put(R.layout.layout_game_name, LAYOUT_LAYOUTGAMENAME);
        sparseIntArray.put(R.layout.layout_game_tag, LAYOUT_LAYOUTGAMETAG);
        sparseIntArray.put(R.layout.layout_game_tag2, LAYOUT_LAYOUTGAMETAG2);
        sparseIntArray.put(R.layout.layout_game_tag3, LAYOUT_LAYOUTGAMETAG3);
        sparseIntArray.put(R.layout.layout_tab_text, LAYOUT_LAYOUTTABTEXT);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 1:
                if ("layout/activity_address_0".equals(tag)) {
                    return new ActivityAddressBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_address is invalid. Received: " + tag);
            case 2:
                if ("layout/activity_alipay_bind_0".equals(tag)) {
                    return new ActivityAlipayBindBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_alipay_bind is invalid. Received: " + tag);
            case 3:
                if ("layout/activity_auth_0".equals(tag)) {
                    return new ActivityAuthBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_auth is invalid. Received: " + tag);
            case 4:
                if ("layout/activity_baofu_0".equals(tag)) {
                    return new ActivityBaofuBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_baofu is invalid. Received: " + tag);
            case 5:
                if ("layout/activity_bbs_detail_0".equals(tag)) {
                    return new ActivityBbsDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_bbs_detail is invalid. Received: " + tag);
            case 6:
                if ("layout/activity_bbs_detail2_0".equals(tag)) {
                    return new ActivityBbsDetail2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_bbs_detail2 is invalid. Received: " + tag);
            case 7:
                if ("layout/activity_bbs_edit_0".equals(tag)) {
                    return new ActivityBbsEditBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_bbs_edit is invalid. Received: " + tag);
            case 8:
                if ("layout/activity_bbs_message_0".equals(tag)) {
                    return new ActivityBbsMessageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_bbs_message is invalid. Received: " + tag);
            case 9:
                if ("layout/activity_bbs_search_0".equals(tag)) {
                    return new ActivityBbsSearchBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_bbs_search is invalid. Received: " + tag);
            case 10:
                if ("layout/activity_boss_server_0".equals(tag)) {
                    return new ActivityBossServerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_boss_server is invalid. Received: " + tag);
            case 11:
                if ("layout/activity_cancellation_0".equals(tag)) {
                    return new ActivityCancellationBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_cancellation is invalid. Received: " + tag);
            case 12:
                if ("layout/activity_card_0".equals(tag)) {
                    return new ActivityCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_card is invalid. Received: " + tag);
            case 13:
                if ("layout/activity_championship_0".equals(tag)) {
                    return new ActivityChampionshipBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_championship is invalid. Received: " + tag);
            case 14:
                if ("layout/activity_change_password_0".equals(tag)) {
                    return new ActivityChangePasswordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_change_password is invalid. Received: " + tag);
            case 15:
                if ("layout/activity_comment_detail_0".equals(tag)) {
                    return new ActivityCommentDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_comment_detail is invalid. Received: " + tag);
            case 16:
                if ("layout/activity_comment_edit_0".equals(tag)) {
                    return new ActivityCommentEditBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_comment_edit is invalid. Received: " + tag);
            case 17:
                if ("layout/activity_daily_coupon_0".equals(tag)) {
                    return new ActivityDailyCouponBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_daily_coupon is invalid. Received: " + tag);
            case 18:
                if ("layout/activity_daily_task_0".equals(tag)) {
                    return new ActivityDailyTaskBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_daily_task is invalid. Received: " + tag);
            case 19:
                if ("layout/activity_deal_detail_0".equals(tag)) {
                    return new ActivityDealDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_deal_detail is invalid. Received: " + tag);
            case 20:
                if ("layout/activity_deal_list_0".equals(tag)) {
                    return new ActivityDealListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_deal_list is invalid. Received: " + tag);
            case 21:
                if ("layout/activity_deal_record_0".equals(tag)) {
                    return new ActivityDealRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_deal_record is invalid. Received: " + tag);
            case 22:
                if ("layout/activity_deal_sell_0".equals(tag)) {
                    return new ActivityDealSellBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_deal_sell is invalid. Received: " + tag);
            case 23:
                if ("layout/activity_deal_sell_info_0".equals(tag)) {
                    return new ActivityDealSellInfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_deal_sell_info is invalid. Received: " + tag);
            case 24:
                if ("layout/activity_develop_0".equals(tag)) {
                    return new ActivityDevelopBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_develop is invalid. Received: " + tag);
            case 25:
                if ("layout/activity_download_0".equals(tag)) {
                    return new ActivityDownloadBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_download is invalid. Received: " + tag);
            case 26:
                if ("layout/activity_empty_0".equals(tag)) {
                    return new ActivityEmptyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_empty is invalid. Received: " + tag);
            case 27:
                if ("layout/activity_event_detail_0".equals(tag)) {
                    return new ActivityEventDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_event_detail is invalid. Received: " + tag);
            case 28:
                if ("layout/activity_feedback_0".equals(tag)) {
                    return new ActivityFeedbackBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_feedback is invalid. Received: " + tag);
            case 29:
                if ("layout/activity_fragment_0".equals(tag)) {
                    return new ActivityFragmentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_fragment is invalid. Received: " + tag);
            case 30:
                if ("layout/activity_fudai_index_0".equals(tag)) {
                    return new ActivityFudaiIndexBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_fudai_index is invalid. Received: " + tag);
            case 31:
                if ("layout/activity_game_coupon_0".equals(tag)) {
                    return new ActivityGameCouponBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_game_coupon is invalid. Received: " + tag);
            case 32:
                if ("layout/activity_game_detail_0".equals(tag)) {
                    return new ActivityGameDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_game_detail is invalid. Received: " + tag);
            case 33:
                if ("layout/activity_game_report_0".equals(tag)) {
                    return new ActivityGameReportBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_game_report is invalid. Received: " + tag);
            case 34:
                if ("layout/activity_game_report2_0".equals(tag)) {
                    return new ActivityGameReport2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_game_report2 is invalid. Received: " + tag);
            case 35:
                if ("layout/activity_gift_detail_0".equals(tag)) {
                    return new ActivityGiftDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_gift_detail is invalid. Received: " + tag);
            case 36:
                if ("layout/activity_gm_0".equals(tag)) {
                    return new ActivityGmBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_gm is invalid. Received: " + tag);
            case 37:
                if ("layout/activity_gm_pay_0".equals(tag)) {
                    return new ActivityGmPayBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_gm_pay is invalid. Received: " + tag);
            case 38:
                if ("layout/activity_gm_trans_0".equals(tag)) {
                    return new ActivityGmTransBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_gm_trans is invalid. Received: " + tag);
            case 39:
                if ("layout/activity_gm_trans_pay_0".equals(tag)) {
                    return new ActivityGmTransPayBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_gm_trans_pay is invalid. Received: " + tag);
            case 40:
                if ("layout/activity_image_0".equals(tag)) {
                    return new ActivityImageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_image is invalid. Received: " + tag);
            case 41:
                if ("layout/activity_info_0".equals(tag)) {
                    return new ActivityInfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_info is invalid. Received: " + tag);
            case 42:
                if ("layout/activity_invite3_0".equals(tag)) {
                    return new ActivityInvite3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_invite3 is invalid. Received: " + tag);
            case 43:
                if ("layout/activity_invite_withdrew_0".equals(tag)) {
                    return new ActivityInviteWithdrewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_invite_withdrew is invalid. Received: " + tag);
            case 44:
                if ("layout/activity_item_sell_0".equals(tag)) {
                    return new ActivityItemSellBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_item_sell is invalid. Received: " + tag);
            case 45:
                if ("layout/activity_item_sell_game_0".equals(tag)) {
                    return new ActivityItemSellGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_item_sell_game is invalid. Received: " + tag);
            case 46:
                if ("layout/activity_item_sell_record_0".equals(tag)) {
                    return new ActivityItemSellRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_item_sell_record is invalid. Received: " + tag);
            case 47:
                if ("layout/activity_item_trade_detail_0".equals(tag)) {
                    return new ActivityItemTradeDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_item_trade_detail is invalid. Received: " + tag);
            case 48:
                if ("layout/activity_jf_record_0".equals(tag)) {
                    return new ActivityJfRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_jf_record is invalid. Received: " + tag);
            case 49:
                if ("layout/activity_login_0".equals(tag)) {
                    return new ActivityLoginBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
            case 50:
                if ("layout/activity_lottery_0".equals(tag)) {
                    return new ActivityLotteryBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_lottery is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 51:
                if ("layout/activity_lottery_record_0".equals(tag)) {
                    return new ActivityLotteryRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_lottery_record is invalid. Received: " + tag);
            case 52:
                if ("layout/activity_main_0".equals(tag)) {
                    return new ActivityMainBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
            case 53:
                if ("layout/activity_message_0".equals(tag)) {
                    return new ActivityMessageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_message is invalid. Received: " + tag);
            case 54:
                if ("layout/activity_month_card_0".equals(tag)) {
                    return new ActivityMonthCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_month_card is invalid. Received: " + tag);
            case 55:
                if ("layout/activity_monthly_task_0".equals(tag)) {
                    return new ActivityMonthlyTaskBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_monthly_task is invalid. Received: " + tag);
            case 56:
                if ("layout/activity_my_money_0".equals(tag)) {
                    return new ActivityMyMoneyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_my_money is invalid. Received: " + tag);
            case 57:
                if ("layout/activity_my_welfare_0".equals(tag)) {
                    return new ActivityMyWelfareBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_my_welfare is invalid. Received: " + tag);
            case 58:
                if ("layout/activity_novice_task_0".equals(tag)) {
                    return new ActivityNoviceTaskBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_novice_task is invalid. Received: " + tag);
            case 59:
                if ("layout/activity_novice_welfare_0".equals(tag)) {
                    return new ActivityNoviceWelfareBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_novice_welfare is invalid. Received: " + tag);
            case 60:
                if ("layout/activity_phone_0".equals(tag)) {
                    return new ActivityPhoneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_phone is invalid. Received: " + tag);
            case 61:
                if ("layout/activity_pin_0".equals(tag)) {
                    return new ActivityPinBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_pin is invalid. Received: " + tag);
            case 62:
                if ("layout/activity_ptb_0".equals(tag)) {
                    return new ActivityPtbBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_ptb is invalid. Received: " + tag);
            case 63:
                if ("layout/activity_ptb_detail_0".equals(tag)) {
                    return new ActivityPtbDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_ptb_detail is invalid. Received: " + tag);
            case 64:
                if ("layout/activity_qiandao_0".equals(tag)) {
                    return new ActivityQiandaoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_qiandao is invalid. Received: " + tag);
            case 65:
                if ("layout/activity_qiandao_record_0".equals(tag)) {
                    return new ActivityQiandaoRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_qiandao_record is invalid. Received: " + tag);
            case 66:
                if ("layout/activity_quick_login_0".equals(tag)) {
                    return new ActivityQuickLoginBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_quick_login is invalid. Received: " + tag);
            case 67:
                if ("layout/activity_record_detail_0".equals(tag)) {
                    return new ActivityRecordDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_record_detail is invalid. Received: " + tag);
            case 68:
                if ("layout/activity_recycle_0".equals(tag)) {
                    return new ActivityRecycleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_recycle is invalid. Received: " + tag);
            case 69:
                if ("layout/activity_register_0".equals(tag)) {
                    return new ActivityRegisterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_register is invalid. Received: " + tag);
            case 70:
                if ("layout/activity_rv_0".equals(tag)) {
                    return new ActivityRvBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_rv is invalid. Received: " + tag);
            case 71:
                if ("layout/activity_rv_tab_0".equals(tag)) {
                    return new ActivityRvTabBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_rv_tab is invalid. Received: " + tag);
            case 72:
                if ("layout/activity_safe_0".equals(tag)) {
                    return new ActivitySafeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_safe is invalid. Received: " + tag);
            case 73:
                if ("layout/activity_sanbao_0".equals(tag)) {
                    return new ActivitySanbaoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_sanbao is invalid. Received: " + tag);
            case 74:
                if ("layout/activity_search_0".equals(tag)) {
                    return new ActivitySearchBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_search is invalid. Received: " + tag);
            case 75:
                if ("layout/activity_service_0".equals(tag)) {
                    return new ActivityServiceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_service is invalid. Received: " + tag);
            case 76:
                if ("layout/activity_service_detail_0".equals(tag)) {
                    return new ActivityServiceDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_service_detail is invalid. Received: " + tag);
            case 77:
                if ("layout/activity_setting_0".equals(tag)) {
                    return new ActivitySettingBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_setting is invalid. Received: " + tag);
            case 78:
                if ("layout/activity_splash_0".equals(tag)) {
                    return new ActivitySplashBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_splash is invalid. Received: " + tag);
            case 79:
                if ("layout/activity_subscribe_0".equals(tag)) {
                    return new ActivitySubscribeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_subscribe is invalid. Received: " + tag);
            case 80:
                if ("layout/activity_task_hall_0".equals(tag)) {
                    return new ActivityTaskHallBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_task_hall is invalid. Received: " + tag);
            case 81:
                if ("layout/activity_topic_detail_0".equals(tag)) {
                    return new ActivityTopicDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_topic_detail is invalid. Received: " + tag);
            case 82:
                if ("layout/activity_trumpet_0".equals(tag)) {
                    return new ActivityTrumpetBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_trumpet is invalid. Received: " + tag);
            case 83:
                if ("layout/activity_user_0".equals(tag)) {
                    return new ActivityUserBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_user is invalid. Received: " + tag);
            case 84:
                if ("layout/activity_video_0".equals(tag)) {
                    return new ActivityVideoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_video is invalid. Received: " + tag);
            case 85:
                if ("layout/activity_vip_0".equals(tag)) {
                    return new ActivityVipBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_vip is invalid. Received: " + tag);
            case 86:
                if ("layout/activity_vip_coupon_0".equals(tag)) {
                    return new ActivityVipCouponBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_vip_coupon is invalid. Received: " + tag);
            case 87:
                if ("layout/activity_web_0".equals(tag)) {
                    return new ActivityWebBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_web is invalid. Received: " + tag);
            case 88:
                if ("layout/activity_web2_0".equals(tag)) {
                    return new ActivityWeb2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_web2 is invalid. Received: " + tag);
            case 89:
                if ("layout/activity_web_pay_0".equals(tag)) {
                    return new ActivityWebPayBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_web_pay is invalid. Received: " + tag);
            case 90:
                if ("layout/activity_welfare_event_detail_0".equals(tag)) {
                    return new ActivityWelfareEventDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_welfare_event_detail is invalid. Received: " + tag);
            case 91:
                if ("layout/activity_withdrew_0".equals(tag)) {
                    return new ActivityWithdrewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_withdrew is invalid. Received: " + tag);
            case 92:
                if ("layout/activity_yun_buy_0".equals(tag)) {
                    return new ActivityYunBuyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_yun_buy is invalid. Received: " + tag);
            case 93:
                if ("layout/activity_yun_play_0".equals(tag)) {
                    return new ActivityYunPlayBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_yun_play is invalid. Received: " + tag);
            case 94:
                if ("layout/dialog_bbs_game_0".equals(tag)) {
                    return new DialogBbsGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_bbs_game is invalid. Received: " + tag);
            case 95:
                if ("layout/dialog_bbs_reply_0".equals(tag)) {
                    return new DialogBbsReplyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_bbs_reply is invalid. Received: " + tag);
            case 96:
                if ("layout/dialog_bbs_sign_0".equals(tag)) {
                    return new DialogBbsSignBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_bbs_sign is invalid. Received: " + tag);
            case 97:
                if ("layout/dialog_boss_server_0".equals(tag)) {
                    return new DialogBossServerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_boss_server is invalid. Received: " + tag);
            case 98:
                if ("layout/dialog_bottom_tip_0".equals(tag)) {
                    return new DialogBottomTipBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_bottom_tip is invalid. Received: " + tag);
            case 99:
                if ("layout/dialog_bottom_tip2_0".equals(tag)) {
                    return new DialogBottomTip2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_bottom_tip2 is invalid. Received: " + tag);
            case 100:
                if ("layout/dialog_comment_0".equals(tag)) {
                    return new DialogCommentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_comment is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding2(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 101:
                if ("layout/dialog_confirm_0".equals(tag)) {
                    return new DialogConfirmBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_confirm is invalid. Received: " + tag);
            case 102:
                if ("layout/dialog_daily_coupon_receive_0".equals(tag)) {
                    return new DialogDailyCouponReceiveBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_daily_coupon_receive is invalid. Received: " + tag);
            case 103:
                if ("layout/dialog_deal_buy_notice_0".equals(tag)) {
                    return new DialogDealBuyNoticeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_deal_buy_notice is invalid. Received: " + tag);
            case 104:
                if ("layout/dialog_deal_detail_0".equals(tag)) {
                    return new DialogDealDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_deal_detail is invalid. Received: " + tag);
            case 105:
                if ("layout/dialog_deal_dicker_0".equals(tag)) {
                    return new DialogDealDickerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_deal_dicker is invalid. Received: " + tag);
            case 106:
                if ("layout/dialog_deal_filter_0".equals(tag)) {
                    return new DialogDealFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_deal_filter is invalid. Received: " + tag);
            case 107:
                if ("layout/dialog_deal_offset_0".equals(tag)) {
                    return new DialogDealOffsetBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_deal_offset is invalid. Received: " + tag);
            case 108:
                if ("layout/dialog_deal_played_0".equals(tag)) {
                    return new DialogDealPlayedBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_deal_played is invalid. Received: " + tag);
            case 109:
                if ("layout/dialog_deal_record_type_0".equals(tag)) {
                    return new DialogDealRecordTypeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_deal_record_type is invalid. Received: " + tag);
            case 110:
                if ("layout/dialog_deal_sell_notice1_0".equals(tag)) {
                    return new DialogDealSellNotice1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_deal_sell_notice1 is invalid. Received: " + tag);
            case 111:
                if ("layout/dialog_game_update_detail_0".equals(tag)) {
                    return new DialogGameUpdateDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_game_update_detail is invalid. Received: " + tag);
            case 112:
                if ("layout/dialog_gift_code_0".equals(tag)) {
                    return new DialogGiftCodeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_gift_code is invalid. Received: " + tag);
            case 113:
                if ("layout/dialog_gm_game_0".equals(tag)) {
                    return new DialogGmGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_gm_game is invalid. Received: " + tag);
            case 114:
                if ("layout/dialog_gm_items_0".equals(tag)) {
                    return new DialogGmItemsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_gm_items is invalid. Received: " + tag);
            case 115:
                if ("layout/dialog_gm_log_0".equals(tag)) {
                    return new DialogGmLogBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_gm_log is invalid. Received: " + tag);
            case 116:
                if ("layout/dialog_gm_roles_0".equals(tag)) {
                    return new DialogGmRolesBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_gm_roles is invalid. Received: " + tag);
            case 117:
                if ("layout/dialog_home_0".equals(tag)) {
                    return new DialogHomeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_home is invalid. Received: " + tag);
            case 118:
                if ("layout/dialog_invite_0".equals(tag)) {
                    return new DialogInviteBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_invite is invalid. Received: " + tag);
            case 119:
                if ("layout/dialog_invite_game_0".equals(tag)) {
                    return new DialogInviteGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_invite_game is invalid. Received: " + tag);
            case 120:
                if ("layout/dialog_invite_record_0".equals(tag)) {
                    return new DialogInviteRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_invite_record is invalid. Received: " + tag);
            case 121:
                if ("layout/dialog_item_adjust_0".equals(tag)) {
                    return new DialogItemAdjustBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_item_adjust is invalid. Received: " + tag);
            case 122:
                if ("layout/dialog_logout_0".equals(tag)) {
                    return new DialogLogoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_logout is invalid. Received: " + tag);
            case 123:
                if ("layout/dialog_lottery_result_0".equals(tag)) {
                    return new DialogLotteryResultBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_lottery_result is invalid. Received: " + tag);
            case 124:
                if ("layout/dialog_new_game_type_0".equals(tag)) {
                    return new DialogNewGameTypeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_new_game_type is invalid. Received: " + tag);
            case 125:
                if ("layout/dialog_novice_welfare_0".equals(tag)) {
                    return new DialogNoviceWelfareBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_novice_welfare is invalid. Received: " + tag);
            case 126:
                if ("layout/dialog_pay_0".equals(tag)) {
                    return new DialogPayBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_pay is invalid. Received: " + tag);
            case 127:
                if ("layout/dialog_pic_0".equals(tag)) {
                    return new DialogPicBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_pic is invalid. Received: " + tag);
            case 128:
                if ("layout/dialog_pin_0".equals(tag)) {
                    return new DialogPinBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_pin is invalid. Received: " + tag);
            case 129:
                if ("layout/dialog_privacy_0".equals(tag)) {
                    return new DialogPrivacyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_privacy is invalid. Received: " + tag);
            case 130:
                if ("layout/dialog_qiandao_task_0".equals(tag)) {
                    return new DialogQiandaoTaskBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_qiandao_task is invalid. Received: " + tag);
            case 131:
                if ("layout/dialog_rule_0".equals(tag)) {
                    return new DialogRuleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_rule is invalid. Received: " + tag);
            case 132:
                if ("layout/dialog_select_trumpet_0".equals(tag)) {
                    return new DialogSelectTrumpetBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_select_trumpet is invalid. Received: " + tag);
            case 133:
                if ("layout/dialog_share_0".equals(tag)) {
                    return new DialogShareBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_share is invalid. Received: " + tag);
            case 134:
                if ("layout/dialog_sqk_rule_0".equals(tag)) {
                    return new DialogSqkRuleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_sqk_rule is invalid. Received: " + tag);
            case 135:
                if ("layout/dialog_tip_0".equals(tag)) {
                    return new DialogTipBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_tip is invalid. Received: " + tag);
            case 136:
                if ("layout/dialog_to_thunt_0".equals(tag)) {
                    return new DialogToThuntBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_to_thunt is invalid. Received: " + tag);
            case 137:
                if ("layout/dialog_topic_record_0".equals(tag)) {
                    return new DialogTopicRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_topic_record is invalid. Received: " + tag);
            case 138:
                if ("layout/dialog_topic_rule_0".equals(tag)) {
                    return new DialogTopicRuleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_topic_rule is invalid. Received: " + tag);
            case 139:
                if ("layout/dialog_unable_game_0".equals(tag)) {
                    return new DialogUnableGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_unable_game is invalid. Received: " + tag);
            case 140:
                if ("layout/dialog_update_0".equals(tag)) {
                    return new DialogUpdateBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_update is invalid. Received: " + tag);
            case 141:
                if ("layout/dialog_vip_coupon_buy_0".equals(tag)) {
                    return new DialogVipCouponBuyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_vip_coupon_buy is invalid. Received: " + tag);
            case 142:
                if ("layout/dialog_vip_right2_0".equals(tag)) {
                    return new DialogVipRight2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_vip_right2 is invalid. Received: " + tag);
            case 143:
                if ("layout/dialog_vip_web_0".equals(tag)) {
                    return new DialogVipWebBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_vip_web is invalid. Received: " + tag);
            case 144:
                if ("layout/dialog_wait_0".equals(tag)) {
                    return new DialogWaitBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_wait is invalid. Received: " + tag);
            case 145:
                if ("layout/dialog_welfare3_gift_0".equals(tag)) {
                    return new DialogWelfare3GiftBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_welfare3_gift is invalid. Received: " + tag);
            case 146:
                if ("layout/dialog_welfare3_role_0".equals(tag)) {
                    return new DialogWelfare3RoleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_welfare3_role is invalid. Received: " + tag);
            case 147:
                if ("layout/dialog_withdraw_0".equals(tag)) {
                    return new DialogWithdrawBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_withdraw is invalid. Received: " + tag);
            case 148:
                if ("layout/dialog_withdraw2_0".equals(tag)) {
                    return new DialogWithdraw2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_withdraw2 is invalid. Received: " + tag);
            case 149:
                if ("layout/dialog_yun_device_0".equals(tag)) {
                    return new DialogYunDeviceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_yun_device is invalid. Received: " + tag);
            case 150:
                if ("layout/dialog_yun_game_0".equals(tag)) {
                    return new DialogYunGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for dialog_yun_game is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding3(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case LAYOUT_FRAGMENTBASE /* 151 */:
                if ("layout/fragment_base_0".equals(tag)) {
                    return new FragmentBaseBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_base is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTBBS /* 152 */:
                if ("layout/fragment_bbs_0".equals(tag)) {
                    return new FragmentBbsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_bbs is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTBBS2 /* 153 */:
                if ("layout/fragment_bbs2_0".equals(tag)) {
                    return new FragmentBbs2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_bbs2 is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTBBSINDEX /* 154 */:
                if ("layout/fragment_bbs_index_0".equals(tag)) {
                    return new FragmentBbsIndexBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_bbs_index is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTCANCELLATION1 /* 155 */:
                if ("layout/fragment_cancellation1_0".equals(tag)) {
                    return new FragmentCancellation1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_cancellation1 is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTCANCELLATION2 /* 156 */:
                if ("layout/fragment_cancellation2_0".equals(tag)) {
                    return new FragmentCancellation2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_cancellation2 is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTCANCELLATION3 /* 157 */:
                if ("layout/fragment_cancellation3_0".equals(tag)) {
                    return new FragmentCancellation3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_cancellation3 is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTCANCELLATION4 /* 158 */:
                if ("layout/fragment_cancellation4_0".equals(tag)) {
                    return new FragmentCancellation4BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_cancellation4 is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTDEAL /* 159 */:
                if ("layout/fragment_deal_0".equals(tag)) {
                    return new FragmentDealBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_deal is invalid. Received: " + tag);
            case 160:
                if ("layout/fragment_deal_index_0".equals(tag)) {
                    return new FragmentDealIndexBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_deal_index is invalid. Received: " + tag);
            case 161:
                if ("layout/fragment_deal_record_0".equals(tag)) {
                    return new FragmentDealRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_deal_record is invalid. Received: " + tag);
            case 162:
                if ("layout/fragment_deal_sell_0".equals(tag)) {
                    return new FragmentDealSellBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_deal_sell is invalid. Received: " + tag);
            case 163:
                if ("layout/fragment_game_0".equals(tag)) {
                    return new FragmentGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_game is invalid. Received: " + tag);
            case 164:
                if ("layout/fragment_game_comment_0".equals(tag)) {
                    return new FragmentGameCommentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_game_comment is invalid. Received: " + tag);
            case 165:
                if ("layout/fragment_game_intro_0".equals(tag)) {
                    return new FragmentGameIntroBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_game_intro is invalid. Received: " + tag);
            case 166:
                if ("layout/fragment_game_server_0".equals(tag)) {
                    return new FragmentGameServerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_game_server is invalid. Received: " + tag);
            case 167:
                if ("layout/fragment_game_tool_0".equals(tag)) {
                    return new FragmentGameToolBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_game_tool is invalid. Received: " + tag);
            case 168:
                if ("layout/fragment_hall_0".equals(tag)) {
                    return new FragmentHallBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_hall is invalid. Received: " + tag);
            case 169:
                if ("layout/fragment_hall_game_0".equals(tag)) {
                    return new FragmentHallGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_hall_game is invalid. Received: " + tag);
            case 170:
                if ("layout/fragment_home_0".equals(tag)) {
                    return new FragmentHomeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
            case 171:
                if ("layout/fragment_home_game_0".equals(tag)) {
                    return new FragmentHomeGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_home_game is invalid. Received: " + tag);
            case 172:
                if ("layout/fragment_home_list2_0".equals(tag)) {
                    return new FragmentHomeList2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_home_list2 is invalid. Received: " + tag);
            case 173:
                if ("layout/fragment_home_new_0".equals(tag)) {
                    return new FragmentHomeNewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_home_new is invalid. Received: " + tag);
            case 174:
                if ("layout/fragment_home_schedule_0".equals(tag)) {
                    return new FragmentHomeScheduleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_home_schedule is invalid. Received: " + tag);
            case 175:
                if ("layout/fragment_home_schedule2_0".equals(tag)) {
                    return new FragmentHomeSchedule2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_home_schedule2 is invalid. Received: " + tag);
            case 176:
                if ("layout/fragment_image_0".equals(tag)) {
                    return new FragmentImageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_image is invalid. Received: " + tag);
            case 177:
                if ("layout/fragment_item_trade_0".equals(tag)) {
                    return new FragmentItemTradeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_item_trade is invalid. Received: " + tag);
            case 178:
                if ("layout/fragment_main_0".equals(tag)) {
                    return new FragmentMainBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_main is invalid. Received: " + tag);
            case 179:
                if ("layout/fragment_rv_0".equals(tag)) {
                    return new FragmentRvBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_rv is invalid. Received: " + tag);
            case 180:
                if ("layout/fragment_sanbao648_0".equals(tag)) {
                    return new FragmentSanbao648BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_sanbao648 is invalid. Received: " + tag);
            case 181:
                if ("layout/fragment_schedule_0".equals(tag)) {
                    return new FragmentScheduleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_schedule is invalid. Received: " + tag);
            case 182:
                if ("layout/fragment_subscribe_0".equals(tag)) {
                    return new FragmentSubscribeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_subscribe is invalid. Received: " + tag);
            case 183:
                if ("layout/fragment_subscribe2_0".equals(tag)) {
                    return new FragmentSubscribe2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_subscribe2 is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTTOPICDETAIL /* 184 */:
                if ("layout/fragment_topic_detail_0".equals(tag)) {
                    return new FragmentTopicDetailBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_topic_detail is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTUSER /* 185 */:
                if ("layout/fragment_user_0".equals(tag)) {
                    return new FragmentUserBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_user is invalid. Received: " + tag);
            case 186:
                if ("layout/fragment_vip_right_0".equals(tag)) {
                    return new FragmentVipRightBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vip_right is invalid. Received: " + tag);
            case 187:
                if ("layout/fragment_web_0".equals(tag)) {
                    return new FragmentWebBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_web is invalid. Received: " + tag);
            case 188:
                if ("layout/fragment_welfare3_0".equals(tag)) {
                    return new FragmentWelfare3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_welfare3 is invalid. Received: " + tag);
            case 189:
                if ("layout/fragment_welfare4_0".equals(tag)) {
                    return new FragmentWelfare4BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_welfare4 is invalid. Received: " + tag);
            case 190:
                if ("layout/item_bbs_0".equals(tag)) {
                    return new ItemBbsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs is invalid. Received: " + tag);
            case 191:
                if ("layout/item_bbs_cate_0".equals(tag)) {
                    return new ItemBbsCateBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs_cate is invalid. Received: " + tag);
            case 192:
                if ("layout/item_bbs_edit_content_0".equals(tag)) {
                    return new ItemBbsEditContentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs_edit_content is invalid. Received: " + tag);
            case LAYOUT_ITEMBBSEDITTAG /* 193 */:
                if ("layout/item_bbs_edit_tag_0".equals(tag)) {
                    return new ItemBbsEditTagBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs_edit_tag is invalid. Received: " + tag);
            case LAYOUT_ITEMBBSGAME /* 194 */:
                if ("layout/item_bbs_game_0".equals(tag)) {
                    return new ItemBbsGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs_game is invalid. Received: " + tag);
            case 195:
                if ("layout/item_bbs_message_index_0".equals(tag)) {
                    return new ItemBbsMessageIndexBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs_message_index is invalid. Received: " + tag);
            case 196:
                if ("layout/item_bbs_message_official_0".equals(tag)) {
                    return new ItemBbsMessageOfficialBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs_message_official is invalid. Received: " + tag);
            case 197:
                if ("layout/item_bbs_message_reply_0".equals(tag)) {
                    return new ItemBbsMessageReplyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs_message_reply is invalid. Received: " + tag);
            case 198:
                if ("layout/item_bbs_search_0".equals(tag)) {
                    return new ItemBbsSearchBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs_search is invalid. Received: " + tag);
            case 199:
                if ("layout/item_bbs_sign_0".equals(tag)) {
                    return new ItemBbsSignBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs_sign is invalid. Received: " + tag);
            case 200:
                if ("layout/item_bbs_sign_welfare_0".equals(tag)) {
                    return new ItemBbsSignWelfareBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs_sign_welfare is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding4(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 201:
                if ("layout/item_bbs_top_0".equals(tag)) {
                    return new ItemBbsTopBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bbs_top is invalid. Received: " + tag);
            case 202:
                if ("layout/item_bill_0".equals(tag)) {
                    return new ItemBillBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_bill is invalid. Received: " + tag);
            case 203:
                if ("layout/item_boss_server2_0".equals(tag)) {
                    return new ItemBossServer2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_boss_server2 is invalid. Received: " + tag);
            case 204:
                if ("layout/item_boss_server_game_0".equals(tag)) {
                    return new ItemBossServerGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_boss_server_game is invalid. Received: " + tag);
            case 205:
                if ("layout/item_card_price_0".equals(tag)) {
                    return new ItemCardPriceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_card_price is invalid. Received: " + tag);
            case 206:
                if ("layout/item_card_record_0".equals(tag)) {
                    return new ItemCardRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_card_record is invalid. Received: " + tag);
            case 207:
                if ("layout/item_card_reward_record_0".equals(tag)) {
                    return new ItemCardRewardRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_card_reward_record is invalid. Received: " + tag);
            case 208:
                if ("layout/item_card_title_0".equals(tag)) {
                    return new ItemCardTitleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_card_title is invalid. Received: " + tag);
            case 209:
                if ("layout/item_championship_reward_0".equals(tag)) {
                    return new ItemChampionshipRewardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_championship_reward is invalid. Received: " + tag);
            case 210:
                if ("layout/item_championship_task_0".equals(tag)) {
                    return new ItemChampionshipTaskBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_championship_task is invalid. Received: " + tag);
            case 211:
                if ("layout/item_comment_category_0".equals(tag)) {
                    return new ItemCommentCategoryBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_comment_category is invalid. Received: " + tag);
            case 212:
                if ("layout/item_comment_content_0".equals(tag)) {
                    return new ItemCommentContentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_comment_content is invalid. Received: " + tag);
            case 213:
                if ("layout/item_comment_tag_0".equals(tag)) {
                    return new ItemCommentTagBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_comment_tag is invalid. Received: " + tag);
            case 214:
                if ("layout/item_daily_coupon_0".equals(tag)) {
                    return new ItemDailyCouponBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_daily_coupon is invalid. Received: " + tag);
            case 215:
                if ("layout/item_deal_0".equals(tag)) {
                    return new ItemDealBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal is invalid. Received: " + tag);
            case 216:
                if ("layout/item_deal_dicker_0".equals(tag)) {
                    return new ItemDealDickerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal_dicker is invalid. Received: " + tag);
            case 217:
                if ("layout/item_deal_filter_type_0".equals(tag)) {
                    return new ItemDealFilterTypeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal_filter_type is invalid. Received: " + tag);
            case 218:
                if ("layout/item_deal_fun_0".equals(tag)) {
                    return new ItemDealFunBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal_fun is invalid. Received: " + tag);
            case 219:
                if ("layout/item_deal_hot_game_0".equals(tag)) {
                    return new ItemDealHotGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal_hot_game is invalid. Received: " + tag);
            case 220:
                if ("layout/item_deal_money_record_0".equals(tag)) {
                    return new ItemDealMoneyRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal_money_record is invalid. Received: " + tag);
            case 221:
                if ("layout/item_deal_pic_0".equals(tag)) {
                    return new ItemDealPicBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal_pic is invalid. Received: " + tag);
            case 222:
                if ("layout/item_deal_played_0".equals(tag)) {
                    return new ItemDealPlayedBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal_played is invalid. Received: " + tag);
            case 223:
                if ("layout/item_deal_record_type_0".equals(tag)) {
                    return new ItemDealRecordTypeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal_record_type is invalid. Received: " + tag);
            case LAYOUT_ITEMDEALROLE /* 224 */:
                if ("layout/item_deal_role_0".equals(tag)) {
                    return new ItemDealRoleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal_role is invalid. Received: " + tag);
            case 225:
                if ("layout/item_deal_sell_0".equals(tag)) {
                    return new ItemDealSellBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal_sell is invalid. Received: " + tag);
            case 226:
                if ("layout/item_deal_sell_child_0".equals(tag)) {
                    return new ItemDealSellChildBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_deal_sell_child is invalid. Received: " + tag);
            case 227:
                if ("layout/item_dialog_home_0".equals(tag)) {
                    return new ItemDialogHomeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_dialog_home is invalid. Received: " + tag);
            case 228:
                if ("layout/item_dialog_invite_game_0".equals(tag)) {
                    return new ItemDialogInviteGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_dialog_invite_game is invalid. Received: " + tag);
            case 229:
                if ("layout/item_dialog_yun_device_0".equals(tag)) {
                    return new ItemDialogYunDeviceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_dialog_yun_device is invalid. Received: " + tag);
            case 230:
                if ("layout/item_download_0".equals(tag)) {
                    return new ItemDownloadBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_download is invalid. Received: " + tag);
            case 231:
                if ("layout/item_feedback_pic_0".equals(tag)) {
                    return new ItemFeedbackPicBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_feedback_pic is invalid. Received: " + tag);
            case LAYOUT_ITEMFUDAIINDEX /* 232 */:
                if ("layout/item_fudai_index_0".equals(tag)) {
                    return new ItemFudaiIndexBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_fudai_index is invalid. Received: " + tag);
            case 233:
                if ("layout/item_fun_0".equals(tag)) {
                    return new ItemFunBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_fun is invalid. Received: " + tag);
            case 234:
                if ("layout/item_game_banner_video_0".equals(tag)) {
                    return new ItemGameBannerVideoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_banner_video is invalid. Received: " + tag);
            case 235:
                if ("layout/item_game_comment_0".equals(tag)) {
                    return new ItemGameCommentBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_comment is invalid. Received: " + tag);
            case 236:
                if ("layout/item_game_comment_sub_0".equals(tag)) {
                    return new ItemGameCommentSubBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_comment_sub is invalid. Received: " + tag);
            case 237:
                if ("layout/item_game_comment_top_0".equals(tag)) {
                    return new ItemGameCommentTopBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_comment_top is invalid. Received: " + tag);
            case 238:
                if ("layout/item_game_coupon_0".equals(tag)) {
                    return new ItemGameCouponBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_coupon is invalid. Received: " + tag);
            case 239:
                if ("layout/item_game_deal_0".equals(tag)) {
                    return new ItemGameDealBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_deal is invalid. Received: " + tag);
            case 240:
                if ("layout/item_game_detail_banner_0".equals(tag)) {
                    return new ItemGameDetailBannerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_detail_banner is invalid. Received: " + tag);
            case 241:
                if ("layout/item_game_detail_chat_0".equals(tag)) {
                    return new ItemGameDetailChatBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_detail_chat is invalid. Received: " + tag);
            case 242:
                if ("layout/item_game_detail_tag_0".equals(tag)) {
                    return new ItemGameDetailTagBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_detail_tag is invalid. Received: " + tag);
            case 243:
                if ("layout/item_game_event_0".equals(tag)) {
                    return new ItemGameEventBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_event is invalid. Received: " + tag);
            case 244:
                if ("layout/item_game_intro1_0".equals(tag)) {
                    return new ItemGameIntro1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_intro1 is invalid. Received: " + tag);
            case 245:
                if ("layout/item_game_intro2_0".equals(tag)) {
                    return new ItemGameIntro2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_intro2 is invalid. Received: " + tag);
            case 246:
                if ("layout/item_game_intro3_0".equals(tag)) {
                    return new ItemGameIntro3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_intro3 is invalid. Received: " + tag);
            case 247:
                if ("layout/item_game_intro4_0".equals(tag)) {
                    return new ItemGameIntro4BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_intro4 is invalid. Received: " + tag);
            case 248:
                if ("layout/item_game_intro_pic_0".equals(tag)) {
                    return new ItemGameIntroPicBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_intro_pic is invalid. Received: " + tag);
            case 249:
                if ("layout/item_game_search_0".equals(tag)) {
                    return new ItemGameSearchBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_search is invalid. Received: " + tag);
            case 250:
                if ("layout/item_game_service_0".equals(tag)) {
                    return new ItemGameServiceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_service is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding5(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 251:
                if ("layout/item_game_tool_0".equals(tag)) {
                    return new ItemGameToolBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_tool is invalid. Received: " + tag);
            case 252:
                if ("layout/item_game_type_0".equals(tag)) {
                    return new ItemGameTypeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_type is invalid. Received: " + tag);
            case 253:
                if ("layout/item_game_type2_0".equals(tag)) {
                    return new ItemGameType2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_type2 is invalid. Received: " + tag);
            case 254:
                if ("layout/item_game_update_0".equals(tag)) {
                    return new ItemGameUpdateBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_update is invalid. Received: " + tag);
            case 255:
                if ("layout/item_game_update2_0".equals(tag)) {
                    return new ItemGameUpdate2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_update2 is invalid. Received: " + tag);
            case 256:
                if ("layout/item_game_vip_0".equals(tag)) {
                    return new ItemGameVipBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_game_vip is invalid. Received: " + tag);
            case 257:
                if ("layout/item_gift_0".equals(tag)) {
                    return new ItemGiftBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_gift is invalid. Received: " + tag);
            case LAYOUT_ITEMGMGAME /* 258 */:
                if ("layout/item_gm_game_0".equals(tag)) {
                    return new ItemGmGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_gm_game is invalid. Received: " + tag);
            case LAYOUT_ITEMGMITEM /* 259 */:
                if ("layout/item_gm_item_0".equals(tag)) {
                    return new ItemGmItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_gm_item is invalid. Received: " + tag);
            case LAYOUT_ITEMGMLOG /* 260 */:
                if ("layout/item_gm_log_0".equals(tag)) {
                    return new ItemGmLogBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_gm_log is invalid. Received: " + tag);
            case LAYOUT_ITEMGMROLE /* 261 */:
                if ("layout/item_gm_role_0".equals(tag)) {
                    return new ItemGmRoleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_gm_role is invalid. Received: " + tag);
            case LAYOUT_ITEMGMTITLE /* 262 */:
                if ("layout/item_gm_title_0".equals(tag)) {
                    return new ItemGmTitleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_gm_title is invalid. Received: " + tag);
            case 263:
                if ("layout/item_group_game_0".equals(tag)) {
                    return new ItemGroupGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_group_game is invalid. Received: " + tag);
            case LAYOUT_ITEMGROUPUSER /* 264 */:
                if ("layout/item_group_user_0".equals(tag)) {
                    return new ItemGroupUserBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_group_user is invalid. Received: " + tag);
            case LAYOUT_ITEMHALLFILTER /* 265 */:
                if ("layout/item_hall_filter_0".equals(tag)) {
                    return new ItemHallFilterBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_hall_filter is invalid. Received: " + tag);
            case LAYOUT_ITEMHALLGAME /* 266 */:
                if ("layout/item_hall_game_0".equals(tag)) {
                    return new ItemHallGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_hall_game is invalid. Received: " + tag);
            case LAYOUT_ITEMHALLGAMETYPE /* 267 */:
                if ("layout/item_hall_game_type_0".equals(tag)) {
                    return new ItemHallGameTypeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_hall_game_type is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEC1 /* 268 */:
                if ("layout/item_home_c1_0".equals(tag)) {
                    return new ItemHomeC1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_c1 is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEC2 /* 269 */:
                if ("layout/item_home_c2_0".equals(tag)) {
                    return new ItemHomeC2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_c2 is invalid. Received: " + tag);
            case 270:
                if ("layout/item_home_c3_0".equals(tag)) {
                    return new ItemHomeC3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_c3 is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEC4 /* 271 */:
                if ("layout/item_home_c4_0".equals(tag)) {
                    return new ItemHomeC4BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_c4 is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMECATE /* 272 */:
                if ("layout/item_home_cate_0".equals(tag)) {
                    return new ItemHomeCateBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_cate is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEGAME /* 273 */:
                if ("layout/item_home_game_0".equals(tag)) {
                    return new ItemHomeGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_game is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEGAME2 /* 274 */:
                if ("layout/item_home_game2_0".equals(tag)) {
                    return new ItemHomeGame2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_game2 is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEGAMEHEAD /* 275 */:
                if ("layout/item_home_game_head_0".equals(tag)) {
                    return new ItemHomeGameHeadBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_game_head is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEGROUP /* 276 */:
                if ("layout/item_home_group_0".equals(tag)) {
                    return new ItemHomeGroupBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_group is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMELARGE /* 277 */:
                if ("layout/item_home_large_0".equals(tag)) {
                    return new ItemHomeLargeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_large is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEMINI /* 278 */:
                if ("layout/item_home_mini_0".equals(tag)) {
                    return new ItemHomeMiniBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_mini is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMENEW /* 279 */:
                if ("layout/item_home_new_0".equals(tag)) {
                    return new ItemHomeNewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_new is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEPIC /* 280 */:
                if ("layout/item_home_pic_0".equals(tag)) {
                    return new ItemHomePicBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_pic is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMERANK /* 281 */:
                if ("layout/item_home_rank_0".equals(tag)) {
                    return new ItemHomeRankBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_rank is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMESCHEDULE /* 282 */:
                if ("layout/item_home_schedule_0".equals(tag)) {
                    return new ItemHomeScheduleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_schedule is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMESCHEDULE2 /* 283 */:
                if ("layout/item_home_schedule2_0".equals(tag)) {
                    return new ItemHomeSchedule2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_schedule2 is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMESCHEDULEHEAD /* 284 */:
                if ("layout/item_home_schedule_head_0".equals(tag)) {
                    return new ItemHomeScheduleHeadBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_schedule_head is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMETAB /* 285 */:
                if ("layout/item_home_tab_0".equals(tag)) {
                    return new ItemHomeTabBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_tab is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMETYPE /* 286 */:
                if ("layout/item_home_type_0".equals(tag)) {
                    return new ItemHomeTypeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_type is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEVERTICAL /* 287 */:
                if ("layout/item_home_vertical_0".equals(tag)) {
                    return new ItemHomeVerticalBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_vertical is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEVIDEO1 /* 288 */:
                if ("layout/item_home_video1_0".equals(tag)) {
                    return new ItemHomeVideo1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_video1 is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEVIDEO2 /* 289 */:
                if ("layout/item_home_video2_0".equals(tag)) {
                    return new ItemHomeVideo2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_video2 is invalid. Received: " + tag);
            case LAYOUT_ITEMHOMEVIDEOGAME /* 290 */:
                if ("layout/item_home_video_game_0".equals(tag)) {
                    return new ItemHomeVideoGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_home_video_game is invalid. Received: " + tag);
            case LAYOUT_ITEMINVITELIST /* 291 */:
                if ("layout/item_invite_list_0".equals(tag)) {
                    return new ItemInviteListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_invite_list is invalid. Received: " + tag);
            case LAYOUT_ITEMINVITERANK /* 292 */:
                if ("layout/item_invite_rank_0".equals(tag)) {
                    return new ItemInviteRankBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_invite_rank is invalid. Received: " + tag);
            case LAYOUT_ITEMINVITEWITHDRAWRECORD /* 293 */:
                if ("layout/item_invite_withdraw_record_0".equals(tag)) {
                    return new ItemInviteWithdrawRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_invite_withdraw_record is invalid. Received: " + tag);
            case LAYOUT_ITEMINVITEWITHDREWRECORD /* 294 */:
                if ("layout/item_invite_withdrew_record_0".equals(tag)) {
                    return new ItemInviteWithdrewRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_invite_withdrew_record is invalid. Received: " + tag);
            case LAYOUT_ITEMITEMSELLGAME /* 295 */:
                if ("layout/item_item_sell_game_0".equals(tag)) {
                    return new ItemItemSellGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_item_sell_game is invalid. Received: " + tag);
            case LAYOUT_ITEMITEMSELLORDER /* 296 */:
                if ("layout/item_item_sell_order_0".equals(tag)) {
                    return new ItemItemSellOrderBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_item_sell_order is invalid. Received: " + tag);
            case LAYOUT_ITEMITEMSELLING /* 297 */:
                if ("layout/item_item_selling_0".equals(tag)) {
                    return new ItemItemSellingBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_item_selling is invalid. Received: " + tag);
            case LAYOUT_ITEMITEMTRADE /* 298 */:
                if ("layout/item_item_trade_0".equals(tag)) {
                    return new ItemItemTradeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_item_trade is invalid. Received: " + tag);
            case LAYOUT_ITEMITEMTRADERECORD /* 299 */:
                if ("layout/item_item_trade_record_0".equals(tag)) {
                    return new ItemItemTradeRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_item_trade_record is invalid. Received: " + tag);
            case 300:
                if ("layout/item_item_trade_server_0".equals(tag)) {
                    return new ItemItemTradeServerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_item_trade_server is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding6(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case LAYOUT_ITEMJFRECORD /* 301 */:
                if ("layout/item_jf_record_0".equals(tag)) {
                    return new ItemJfRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_jf_record is invalid. Received: " + tag);
            case LAYOUT_ITEMLOTTERYFUN /* 302 */:
                if ("layout/item_lottery_fun_0".equals(tag)) {
                    return new ItemLotteryFunBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_lottery_fun is invalid. Received: " + tag);
            case LAYOUT_ITEMLOTTERYGIFT /* 303 */:
                if ("layout/item_lottery_gift_0".equals(tag)) {
                    return new ItemLotteryGiftBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_lottery_gift is invalid. Received: " + tag);
            case LAYOUT_ITEMLOTTERYGIFTRESULT /* 304 */:
                if ("layout/item_lottery_gift_result_0".equals(tag)) {
                    return new ItemLotteryGiftResultBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_lottery_gift_result is invalid. Received: " + tag);
            case LAYOUT_ITEMLOTTERYRECORD /* 305 */:
                if ("layout/item_lottery_record_0".equals(tag)) {
                    return new ItemLotteryRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_lottery_record is invalid. Received: " + tag);
            case LAYOUT_ITEMMAINTAB /* 306 */:
                if ("layout/item_main_tab_0".equals(tag)) {
                    return new ItemMainTabBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_main_tab is invalid. Received: " + tag);
            case 307:
                if ("layout/item_message_dicker_0".equals(tag)) {
                    return new ItemMessageDickerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_message_dicker is invalid. Received: " + tag);
            case 308:
                if ("layout/item_message_official_0".equals(tag)) {
                    return new ItemMessageOfficialBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_message_official is invalid. Received: " + tag);
            case LAYOUT_ITEMMESSAGEUSER /* 309 */:
                if ("layout/item_message_user_0".equals(tag)) {
                    return new ItemMessageUserBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_message_user is invalid. Received: " + tag);
            case LAYOUT_ITEMMYCOUPON /* 310 */:
                if ("layout/item_my_coupon_0".equals(tag)) {
                    return new ItemMyCouponBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_my_coupon is invalid. Received: " + tag);
            case LAYOUT_ITEMMYGAME /* 311 */:
                if ("layout/item_my_game_0".equals(tag)) {
                    return new ItemMyGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_my_game is invalid. Received: " + tag);
            case LAYOUT_ITEMMYGIFT /* 312 */:
                if ("layout/item_my_gift_0".equals(tag)) {
                    return new ItemMyGiftBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_my_gift is invalid. Received: " + tag);
            case LAYOUT_ITEMMYVOUCHER /* 313 */:
                if ("layout/item_my_voucher_0".equals(tag)) {
                    return new ItemMyVoucherBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_my_voucher is invalid. Received: " + tag);
            case LAYOUT_ITEMNOVICEGAMECOUPON /* 314 */:
                if ("layout/item_novice_game_coupon_0".equals(tag)) {
                    return new ItemNoviceGameCouponBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_novice_game_coupon is invalid. Received: " + tag);
            case LAYOUT_ITEMNOVICEGAMEGIFT /* 315 */:
                if ("layout/item_novice_game_gift_0".equals(tag)) {
                    return new ItemNoviceGameGiftBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_novice_game_gift is invalid. Received: " + tag);
            case LAYOUT_ITEMPERMISSIONLIST /* 316 */:
                if ("layout/item_permission_list_0".equals(tag)) {
                    return new ItemPermissionListBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_permission_list is invalid. Received: " + tag);
            case LAYOUT_ITEMPICSELECT /* 317 */:
                if ("layout/item_pic_select_0".equals(tag)) {
                    return new ItemPicSelectBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_pic_select is invalid. Received: " + tag);
            case LAYOUT_ITEMPOINTRECORD /* 318 */:
                if ("layout/item_point_record_0".equals(tag)) {
                    return new ItemPointRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_point_record is invalid. Received: " + tag);
            case LAYOUT_ITEMPROMBLE /* 319 */:
                if ("layout/item_promble_0".equals(tag)) {
                    return new ItemPrombleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_promble is invalid. Received: " + tag);
            case LAYOUT_ITEMQIANDAO /* 320 */:
                if ("layout/item_qiandao_0".equals(tag)) {
                    return new ItemQiandaoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_qiandao is invalid. Received: " + tag);
            case LAYOUT_ITEMQIANDAORECORD /* 321 */:
                if ("layout/item_qiandao_record_0".equals(tag)) {
                    return new ItemQiandaoRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_qiandao_record is invalid. Received: " + tag);
            case LAYOUT_ITEMQIANDAOTASK /* 322 */:
                if ("layout/item_qiandao_task_0".equals(tag)) {
                    return new ItemQiandaoTaskBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_qiandao_task is invalid. Received: " + tag);
            case LAYOUT_ITEMQIANDAOUSER /* 323 */:
                if ("layout/item_qiandao_user_0".equals(tag)) {
                    return new ItemQiandaoUserBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_qiandao_user is invalid. Received: " + tag);
            case LAYOUT_ITEMRECYCLE /* 324 */:
                if ("layout/item_recycle_0".equals(tag)) {
                    return new ItemRecycleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_recycle is invalid. Received: " + tag);
            case LAYOUT_ITEMRECYCLEACCOUNT /* 325 */:
                if ("layout/item_recycle_account_0".equals(tag)) {
                    return new ItemRecycleAccountBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_recycle_account is invalid. Received: " + tag);
            case LAYOUT_ITEMRECYCLERECORD /* 326 */:
                if ("layout/item_recycle_record_0".equals(tag)) {
                    return new ItemRecycleRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_recycle_record is invalid. Received: " + tag);
            case LAYOUT_ITEMREPORTPIC /* 327 */:
                if ("layout/item_report_pic_0".equals(tag)) {
                    return new ItemReportPicBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_report_pic is invalid. Received: " + tag);
            case LAYOUT_ITEMSANBAO648 /* 328 */:
                if ("layout/item_sanbao_648_0".equals(tag)) {
                    return new ItemSanbao648BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_sanbao_648 is invalid. Received: " + tag);
            case LAYOUT_ITEMSANBAO648COUPON /* 329 */:
                if ("layout/item_sanbao_648_coupon_0".equals(tag)) {
                    return new ItemSanbao648CouponBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_sanbao_648_coupon is invalid. Received: " + tag);
            case LAYOUT_ITEMSANBAO648GIFT /* 330 */:
                if ("layout/item_sanbao_648_gift_0".equals(tag)) {
                    return new ItemSanbao648GiftBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_sanbao_648_gift is invalid. Received: " + tag);
            case LAYOUT_ITEMSANBAOGAME /* 331 */:
                if ("layout/item_sanbao_game_0".equals(tag)) {
                    return new ItemSanbaoGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_sanbao_game is invalid. Received: " + tag);
            case LAYOUT_ITEMSANBAOGAME2 /* 332 */:
                if ("layout/item_sanbao_game2_0".equals(tag)) {
                    return new ItemSanbaoGame2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_sanbao_game2 is invalid. Received: " + tag);
            case LAYOUT_ITEMSANBAOMESSAGE /* 333 */:
                if ("layout/item_sanbao_message_0".equals(tag)) {
                    return new ItemSanbaoMessageBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_sanbao_message is invalid. Received: " + tag);
            case LAYOUT_ITEMSCHEDULETIME /* 334 */:
                if ("layout/item_schedule_time_0".equals(tag)) {
                    return new ItemScheduleTimeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_schedule_time is invalid. Received: " + tag);
            case LAYOUT_ITEMSCHEDULETYPE /* 335 */:
                if ("layout/item_schedule_type_0".equals(tag)) {
                    return new ItemScheduleTypeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_schedule_type is invalid. Received: " + tag);
            case LAYOUT_ITEMSEARCHHISTORY /* 336 */:
                if ("layout/item_search_history_0".equals(tag)) {
                    return new ItemSearchHistoryBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_search_history is invalid. Received: " + tag);
            case LAYOUT_ITEMSEARCHHOT /* 337 */:
                if ("layout/item_search_hot_0".equals(tag)) {
                    return new ItemSearchHotBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_search_hot is invalid. Received: " + tag);
            case LAYOUT_ITEMSEARCHTYPE /* 338 */:
                if ("layout/item_search_type_0".equals(tag)) {
                    return new ItemSearchTypeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_search_type is invalid. Received: " + tag);
            case LAYOUT_ITEMSELECTTRUMPET /* 339 */:
                if ("layout/item_select_trumpet_0".equals(tag)) {
                    return new ItemSelectTrumpetBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_select_trumpet is invalid. Received: " + tag);
            case LAYOUT_ITEMSERVICE /* 340 */:
                if ("layout/item_service_0".equals(tag)) {
                    return new ItemServiceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_service is invalid. Received: " + tag);
            case LAYOUT_ITEMSERVICEPROBLEM /* 341 */:
                if ("layout/item_service_problem_0".equals(tag)) {
                    return new ItemServiceProblemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_service_problem is invalid. Received: " + tag);
            case LAYOUT_ITEMTASK /* 342 */:
                if ("layout/item_task_0".equals(tag)) {
                    return new ItemTaskBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_task is invalid. Received: " + tag);
            case LAYOUT_ITEMTASKDAILY /* 343 */:
                if ("layout/item_task_daily_0".equals(tag)) {
                    return new ItemTaskDailyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_task_daily is invalid. Received: " + tag);
            case LAYOUT_ITEMTASKMONTHLY /* 344 */:
                if ("layout/item_task_monthly_0".equals(tag)) {
                    return new ItemTaskMonthlyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_task_monthly is invalid. Received: " + tag);
            case LAYOUT_ITEMTOPICBANNER /* 345 */:
                if ("layout/item_topic_banner_0".equals(tag)) {
                    return new ItemTopicBannerBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_topic_banner is invalid. Received: " + tag);
            case LAYOUT_ITEMTOPICLOTTERY /* 346 */:
                if ("layout/item_topic_lottery_0".equals(tag)) {
                    return new ItemTopicLotteryBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_topic_lottery is invalid. Received: " + tag);
            case LAYOUT_ITEMTOPICRECORD /* 347 */:
                if ("layout/item_topic_record_0".equals(tag)) {
                    return new ItemTopicRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_topic_record is invalid. Received: " + tag);
            case LAYOUT_ITEMTOPICTASK /* 348 */:
                if ("layout/item_topic_task_0".equals(tag)) {
                    return new ItemTopicTaskBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_topic_task is invalid. Received: " + tag);
            case LAYOUT_ITEMTRUMPET1 /* 349 */:
                if ("layout/item_trumpet1_0".equals(tag)) {
                    return new ItemTrumpet1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_trumpet1 is invalid. Received: " + tag);
            case LAYOUT_ITEMTRUMPET2 /* 350 */:
                if ("layout/item_trumpet2_0".equals(tag)) {
                    return new ItemTrumpet2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_trumpet2 is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding7(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case LAYOUT_ITEMUNABLEGAME /* 351 */:
                if ("layout/item_unable_game_0".equals(tag)) {
                    return new ItemUnableGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_unable_game is invalid. Received: " + tag);
            case LAYOUT_ITEMVIPCOUPON /* 352 */:
                if ("layout/item_vip_coupon_0".equals(tag)) {
                    return new ItemVipCouponBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_vip_coupon is invalid. Received: " + tag);
            case LAYOUT_ITEMVIPCOUPONLEVEL /* 353 */:
                if ("layout/item_vip_coupon_level_0".equals(tag)) {
                    return new ItemVipCouponLevelBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_vip_coupon_level is invalid. Received: " + tag);
            case LAYOUT_ITEMVIPFLB /* 354 */:
                if ("layout/item_vip_flb_0".equals(tag)) {
                    return new ItemVipFlbBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_vip_flb is invalid. Received: " + tag);
            case LAYOUT_ITEMVIPFUN /* 355 */:
                if ("layout/item_vip_fun_0".equals(tag)) {
                    return new ItemVipFunBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_vip_fun is invalid. Received: " + tag);
            case LAYOUT_ITEMVIPGIFT /* 356 */:
                if ("layout/item_vip_gift_0".equals(tag)) {
                    return new ItemVipGiftBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_vip_gift is invalid. Received: " + tag);
            case LAYOUT_ITEMVIPGIFTSMALL /* 357 */:
                if ("layout/item_vip_gift_small_0".equals(tag)) {
                    return new ItemVipGiftSmallBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_vip_gift_small is invalid. Received: " + tag);
            case LAYOUT_ITEMVIPRIGHT /* 358 */:
                if ("layout/item_vip_right_0".equals(tag)) {
                    return new ItemVipRightBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_vip_right is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFARE31 /* 359 */:
                if ("layout/item_welfare3_1_0".equals(tag)) {
                    return new ItemWelfare31BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare3_1 is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFARE3GAME /* 360 */:
                if ("layout/item_welfare3_game_0".equals(tag)) {
                    return new ItemWelfare3GameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare3_game is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFARE3GAMESMALL /* 361 */:
                if ("layout/item_welfare3_game_small_0".equals(tag)) {
                    return new ItemWelfare3GameSmallBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare3_game_small is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFARE3GIFT /* 362 */:
                if ("layout/item_welfare3_gift_0".equals(tag)) {
                    return new ItemWelfare3GiftBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare3_gift is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFARE3GIFT2 /* 363 */:
                if ("layout/item_welfare3_gift2_0".equals(tag)) {
                    return new ItemWelfare3Gift2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare3_gift2 is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFARE3ROLE /* 364 */:
                if ("layout/item_welfare3_role_0".equals(tag)) {
                    return new ItemWelfare3RoleBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare3_role is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFARECARD /* 365 */:
                if ("layout/item_welfare_card_0".equals(tag)) {
                    return new ItemWelfareCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare_card is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFAREEVENTREPLY /* 366 */:
                if ("layout/item_welfare_event_reply_0".equals(tag)) {
                    return new ItemWelfareEventReplyBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare_event_reply is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFAREQIANDAO /* 367 */:
                if ("layout/item_welfare_qiandao_0".equals(tag)) {
                    return new ItemWelfareQiandaoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare_qiandao is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFARETASK /* 368 */:
                if ("layout/item_welfare_task_0".equals(tag)) {
                    return new ItemWelfareTaskBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare_task is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFARETASK2 /* 369 */:
                if ("layout/item_welfare_task2_0".equals(tag)) {
                    return new ItemWelfareTask2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare_task2 is invalid. Received: " + tag);
            case LAYOUT_ITEMWELFARETASK3 /* 370 */:
                if ("layout/item_welfare_task3_0".equals(tag)) {
                    return new ItemWelfareTask3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_welfare_task3 is invalid. Received: " + tag);
            case LAYOUT_ITEMWITHDREWRECORD /* 371 */:
                if ("layout/item_withdrew_record_0".equals(tag)) {
                    return new ItemWithdrewRecordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_withdrew_record is invalid. Received: " + tag);
            case LAYOUT_ITEMYUNBLOCK /* 372 */:
                if ("layout/item_yun_block_0".equals(tag)) {
                    return new ItemYunBlockBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_yun_block is invalid. Received: " + tag);
            case LAYOUT_ITEMYUNGAME /* 373 */:
                if ("layout/item_yun_game_0".equals(tag)) {
                    return new ItemYunGameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_yun_game is invalid. Received: " + tag);
            case LAYOUT_ITEMYUNPOPDEVICE /* 374 */:
                if ("layout/item_yun_pop_device_0".equals(tag)) {
                    return new ItemYunPopDeviceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_yun_pop_device is invalid. Received: " + tag);
            case LAYOUT_ITEMYUNPRICE /* 375 */:
                if ("layout/item_yun_price_0".equals(tag)) {
                    return new ItemYunPriceBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_yun_price is invalid. Received: " + tag);
            case LAYOUT_ITEMYUNTIP /* 376 */:
                if ("layout/item_yun_tip_0".equals(tag)) {
                    return new ItemYunTipBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for item_yun_tip is invalid. Received: " + tag);
            case LAYOUT_LAYOUTBANNERBBS /* 377 */:
                if ("layout/layout_banner_bbs_0".equals(tag)) {
                    return new LayoutBannerBbsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_banner_bbs is invalid. Received: " + tag);
            case LAYOUT_LAYOUTBANNERHOME /* 378 */:
                if ("layout/layout_banner_home_0".equals(tag)) {
                    return new LayoutBannerHomeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_banner_home is invalid. Received: " + tag);
            case LAYOUT_LAYOUTDEALALL /* 379 */:
                if ("layout/layout_deal_all_0".equals(tag)) {
                    return new LayoutDealAllBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_deal_all is invalid. Received: " + tag);
            case LAYOUT_LAYOUTDISCOUNT /* 380 */:
                if ("layout/layout_discount_0".equals(tag)) {
                    return new LayoutDiscountBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_discount is invalid. Received: " + tag);
            case LAYOUT_LAYOUTGAMEDETAILTAB /* 381 */:
                if ("layout/layout_game_detail_tab_0".equals(tag)) {
                    return new LayoutGameDetailTabBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_game_detail_tab is invalid. Received: " + tag);
            case LAYOUT_LAYOUTGAMEICON /* 382 */:
                if ("layout/layout_game_icon_0".equals(tag)) {
                    return new LayoutGameIconBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_game_icon is invalid. Received: " + tag);
            case LAYOUT_LAYOUTGAMENAME /* 383 */:
                if ("layout/layout_game_name_0".equals(tag)) {
                    return new LayoutGameNameBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_game_name is invalid. Received: " + tag);
            case LAYOUT_LAYOUTGAMETAG /* 384 */:
                if ("layout/layout_game_tag_0".equals(tag)) {
                    return new LayoutGameTagBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_game_tag is invalid. Received: " + tag);
            case LAYOUT_LAYOUTGAMETAG2 /* 385 */:
                if ("layout/layout_game_tag2_0".equals(tag)) {
                    return new LayoutGameTag2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_game_tag2 is invalid. Received: " + tag);
            case LAYOUT_LAYOUTGAMETAG3 /* 386 */:
                if ("layout/layout_game_tag3_0".equals(tag)) {
                    return new LayoutGameTag3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_game_tag3 is invalid. Received: " + tag);
            case LAYOUT_LAYOUTTABTEXT /* 387 */:
                if ("layout/layout_tab_text_0".equals(tag)) {
                    return new LayoutTabTextBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for layout_tab_text is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
        int i = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
        if (i <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        switch ((i - 1) / 50) {
            case 0:
                return internalGetViewDataBinding0(component, view, i, tag);
            case 1:
                return internalGetViewDataBinding1(component, view, i, tag);
            case 2:
                return internalGetViewDataBinding2(component, view, i, tag);
            case 3:
                return internalGetViewDataBinding3(component, view, i, tag);
            case 4:
                return internalGetViewDataBinding4(component, view, i, tag);
            case 5:
                return internalGetViewDataBinding5(component, view, i, tag);
            case 6:
                return internalGetViewDataBinding6(component, view, i, tag);
            case 7:
                return internalGetViewDataBinding7(component, view, i, tag);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
        if (views == null || views.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId) <= 0 || views[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String tag) {
        Integer num;
        if (tag == null || (num = InnerLayoutIdLookup.sKeys.get(tag)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int localId) {
        return InnerBrLookup.sKeys.get(localId);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(129);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "account");
            sparseArray.put(2, "activity");
            sparseArray.put(3, "address");
            sparseArray.put(4, "alipay");
            sparseArray.put(5, "area");
            sparseArray.put(6, "banner");
            sparseArray.put(7, "bind");
            sparseArray.put(8, "boss");
            sparseArray.put(9, "btn");
            sparseArray.put(10, "card");
            sparseArray.put(11, "check");
            sparseArray.put(12, "child");
            sparseArray.put(13, "city");
            sparseArray.put(14, "code");
            sparseArray.put(15, "commentNum");
            sparseArray.put(16, "company");
            sparseArray.put(17, "config");
            sparseArray.put(18, DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT);
            sparseArray.put(19, MetricsSQLiteCacheKt.METRICS_COUNT);
            sparseArray.put(20, "country");
            sparseArray.put(21, "current");
            sparseArray.put(22, Progress.CURRENT_SIZE);
            sparseArray.put(23, "data");
            sparseArray.put(24, DBHelper.COL_DATA3);
            sparseArray.put(25, SocialConstants.PARAM_APP_DESC);
            sparseArray.put(26, SocialConstants.PARAM_COMMENT);
            sparseArray.put(27, "dicker");
            sparseArray.put(28, "done");
            sparseArray.put(29, "downloadCurrentProgress");
            sparseArray.put(30, "downloadText");
            sparseArray.put(31, "favorite");
            sparseArray.put(32, "filter");
            sparseArray.put(33, "fold");
            sparseArray.put(34, Progress.FOLDER);
            sparseArray.put(35, "game");
            sparseArray.put(36, "gift");
            sparseArray.put(37, "group");
            sparseArray.put(38, "hideLine");
            sparseArray.put(39, "hint");
            sparseArray.put(40, "icon");
            sparseArray.put(41, "idcard");
            sparseArray.put(42, SocialConstants.PARAM_IMG_URL);
            sparseArray.put(43, "index");
            sparseArray.put(44, CommonConstants.VALUE_LEVEL_INFO);
            sparseArray.put(45, "intro");
            sparseArray.put(46, "isDicker");
            sparseArray.put(47, "islikeNum");
            sparseArray.put(48, "isreservation");
            sparseArray.put(49, "item");
            sparseArray.put(50, "jianlou");
            sparseArray.put(51, "keyword");
            sparseArray.put(52, "level");
            sparseArray.put(53, "like_num");
            sparseArray.put(54, "manager");
            sparseArray.put(55, "max");
            sparseArray.put(56, "messageNum");
            sparseArray.put(57, "min");
            sparseArray.put(58, "mode");
            sparseArray.put(59, "month");
            sparseArray.put(60, "msgNum");
            sparseArray.put(61, "name");
            sparseArray.put(62, "nav");
            sparseArray.put(63, "num");
            sparseArray.put(64, Constant.LOGIN_ACTIVITY_NUMBER);
            sparseArray.put(65, "oldGame");
            sparseArray.put(66, "oldRole");
            sparseArray.put(67, "oldTitle");
            sparseArray.put(68, "onClick");
            sparseArray.put(69, "order");
            sparseArray.put(70, HintConstants.AUTOFILL_HINT_PASSWORD);
            sparseArray.put(71, "password2");
            sparseArray.put(72, HintConstants.AUTOFILL_HINT_PHONE);
            sparseArray.put(73, "pin");
            sparseArray.put(74, ImageSelector.POSITION);
            sparseArray.put(75, "praise");
            sparseArray.put(76, "praised");
            sparseArray.put(77, "price");
            sparseArray.put(78, "problem");
            sparseArray.put(79, NotificationCompat.CATEGORY_PROGRESS);
            sparseArray.put(80, "province");
            sparseArray.put(81, "ptb");
            sparseArray.put(82, "qiandao");
            sparseArray.put(83, "read");
            sparseArray.put(84, "reason");
            sparseArray.put(85, "renew");
            sparseArray.put(86, "right");
            sparseArray.put(87, "role");
            sparseArray.put(88, "roleId");
            sparseArray.put(89, "search");
            sparseArray.put(90, "select");
            sparseArray.put(91, "selectPrice");
            sparseArray.put(92, "selectPricePosition");
            sparseArray.put(93, "selectType");
            sparseArray.put(94, "selected");
            sparseArray.put(95, "sellMoney");
            sparseArray.put(96, "server");
            sparseArray.put(97, "service");
            sparseArray.put(98, "showContent");
            sparseArray.put(99, "showDickerPrice");
            sparseArray.put(100, "showFree");
            sparseArray.put(101, "showMenu");
            sparseArray.put(102, "showText");
            sparseArray.put(103, "step");
            sparseArray.put(104, "str");
            sparseArray.put(105, Progress.TAG);
            sparseArray.put(106, "target");
            sparseArray.put(107, "task");
            sparseArray.put(108, "task1");
            sparseArray.put(109, "task2");
            sparseArray.put(110, "telephone");
            sparseArray.put(111, "text");
            sparseArray.put(112, "time");
            sparseArray.put(113, "tip");
            sparseArray.put(114, "title");
            sparseArray.put(115, "title2");
            sparseArray.put(116, "top");
            sparseArray.put(117, "topic");
            sparseArray.put(118, "type");
            sparseArray.put(119, "user");
            sparseArray.put(120, "userRole");
            sparseArray.put(121, HintConstants.AUTOFILL_HINT_USERNAME);
            sparseArray.put(122, "video");
            sparseArray.put(123, "vip");
            sparseArray.put(124, "visitor");
            sparseArray.put(125, "white");
            sparseArray.put(126, "wo");
            sparseArray.put(127, "yun");
            sparseArray.put(128, "yzm");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> map = new HashMap<>(DataBinderMapperImpl.LAYOUT_LAYOUTTABTEXT);
            sKeys = map;
            map.put("layout/activity_address_0", Integer.valueOf(R.layout.activity_address));
            map.put("layout/activity_alipay_bind_0", Integer.valueOf(R.layout.activity_alipay_bind));
            map.put("layout/activity_auth_0", Integer.valueOf(R.layout.activity_auth));
            map.put("layout/activity_baofu_0", Integer.valueOf(R.layout.activity_baofu));
            map.put("layout/activity_bbs_detail_0", Integer.valueOf(R.layout.activity_bbs_detail));
            map.put("layout/activity_bbs_detail2_0", Integer.valueOf(R.layout.activity_bbs_detail2));
            map.put("layout/activity_bbs_edit_0", Integer.valueOf(R.layout.activity_bbs_edit));
            map.put("layout/activity_bbs_message_0", Integer.valueOf(R.layout.activity_bbs_message));
            map.put("layout/activity_bbs_search_0", Integer.valueOf(R.layout.activity_bbs_search));
            map.put("layout/activity_boss_server_0", Integer.valueOf(R.layout.activity_boss_server));
            map.put("layout/activity_cancellation_0", Integer.valueOf(R.layout.activity_cancellation));
            map.put("layout/activity_card_0", Integer.valueOf(R.layout.activity_card));
            map.put("layout/activity_championship_0", Integer.valueOf(R.layout.activity_championship));
            map.put("layout/activity_change_password_0", Integer.valueOf(R.layout.activity_change_password));
            map.put("layout/activity_comment_detail_0", Integer.valueOf(R.layout.activity_comment_detail));
            map.put("layout/activity_comment_edit_0", Integer.valueOf(R.layout.activity_comment_edit));
            map.put("layout/activity_daily_coupon_0", Integer.valueOf(R.layout.activity_daily_coupon));
            map.put("layout/activity_daily_task_0", Integer.valueOf(R.layout.activity_daily_task));
            map.put("layout/activity_deal_detail_0", Integer.valueOf(R.layout.activity_deal_detail));
            map.put("layout/activity_deal_list_0", Integer.valueOf(R.layout.activity_deal_list));
            map.put("layout/activity_deal_record_0", Integer.valueOf(R.layout.activity_deal_record));
            map.put("layout/activity_deal_sell_0", Integer.valueOf(R.layout.activity_deal_sell));
            map.put("layout/activity_deal_sell_info_0", Integer.valueOf(R.layout.activity_deal_sell_info));
            map.put("layout/activity_develop_0", Integer.valueOf(R.layout.activity_develop));
            map.put("layout/activity_download_0", Integer.valueOf(R.layout.activity_download));
            map.put("layout/activity_empty_0", Integer.valueOf(R.layout.activity_empty));
            map.put("layout/activity_event_detail_0", Integer.valueOf(R.layout.activity_event_detail));
            map.put("layout/activity_feedback_0", Integer.valueOf(R.layout.activity_feedback));
            map.put("layout/activity_fragment_0", Integer.valueOf(R.layout.activity_fragment));
            map.put("layout/activity_fudai_index_0", Integer.valueOf(R.layout.activity_fudai_index));
            map.put("layout/activity_game_coupon_0", Integer.valueOf(R.layout.activity_game_coupon));
            map.put("layout/activity_game_detail_0", Integer.valueOf(R.layout.activity_game_detail));
            map.put("layout/activity_game_report_0", Integer.valueOf(R.layout.activity_game_report));
            map.put("layout/activity_game_report2_0", Integer.valueOf(R.layout.activity_game_report2));
            map.put("layout/activity_gift_detail_0", Integer.valueOf(R.layout.activity_gift_detail));
            map.put("layout/activity_gm_0", Integer.valueOf(R.layout.activity_gm));
            map.put("layout/activity_gm_pay_0", Integer.valueOf(R.layout.activity_gm_pay));
            map.put("layout/activity_gm_trans_0", Integer.valueOf(R.layout.activity_gm_trans));
            map.put("layout/activity_gm_trans_pay_0", Integer.valueOf(R.layout.activity_gm_trans_pay));
            map.put("layout/activity_image_0", Integer.valueOf(R.layout.activity_image));
            map.put("layout/activity_info_0", Integer.valueOf(R.layout.activity_info));
            map.put("layout/activity_invite3_0", Integer.valueOf(R.layout.activity_invite3));
            map.put("layout/activity_invite_withdrew_0", Integer.valueOf(R.layout.activity_invite_withdrew));
            map.put("layout/activity_item_sell_0", Integer.valueOf(R.layout.activity_item_sell));
            map.put("layout/activity_item_sell_game_0", Integer.valueOf(R.layout.activity_item_sell_game));
            map.put("layout/activity_item_sell_record_0", Integer.valueOf(R.layout.activity_item_sell_record));
            map.put("layout/activity_item_trade_detail_0", Integer.valueOf(R.layout.activity_item_trade_detail));
            map.put("layout/activity_jf_record_0", Integer.valueOf(R.layout.activity_jf_record));
            map.put("layout/activity_login_0", Integer.valueOf(R.layout.activity_login));
            map.put("layout/activity_lottery_0", Integer.valueOf(R.layout.activity_lottery));
            map.put("layout/activity_lottery_record_0", Integer.valueOf(R.layout.activity_lottery_record));
            map.put("layout/activity_main_0", Integer.valueOf(R.layout.activity_main));
            map.put("layout/activity_message_0", Integer.valueOf(R.layout.activity_message));
            map.put("layout/activity_month_card_0", Integer.valueOf(R.layout.activity_month_card));
            map.put("layout/activity_monthly_task_0", Integer.valueOf(R.layout.activity_monthly_task));
            map.put("layout/activity_my_money_0", Integer.valueOf(R.layout.activity_my_money));
            map.put("layout/activity_my_welfare_0", Integer.valueOf(R.layout.activity_my_welfare));
            map.put("layout/activity_novice_task_0", Integer.valueOf(R.layout.activity_novice_task));
            map.put("layout/activity_novice_welfare_0", Integer.valueOf(R.layout.activity_novice_welfare));
            map.put("layout/activity_phone_0", Integer.valueOf(R.layout.activity_phone));
            map.put("layout/activity_pin_0", Integer.valueOf(R.layout.activity_pin));
            map.put("layout/activity_ptb_0", Integer.valueOf(R.layout.activity_ptb));
            map.put("layout/activity_ptb_detail_0", Integer.valueOf(R.layout.activity_ptb_detail));
            map.put("layout/activity_qiandao_0", Integer.valueOf(R.layout.activity_qiandao));
            map.put("layout/activity_qiandao_record_0", Integer.valueOf(R.layout.activity_qiandao_record));
            map.put("layout/activity_quick_login_0", Integer.valueOf(R.layout.activity_quick_login));
            map.put("layout/activity_record_detail_0", Integer.valueOf(R.layout.activity_record_detail));
            map.put("layout/activity_recycle_0", Integer.valueOf(R.layout.activity_recycle));
            map.put("layout/activity_register_0", Integer.valueOf(R.layout.activity_register));
            map.put("layout/activity_rv_0", Integer.valueOf(R.layout.activity_rv));
            map.put("layout/activity_rv_tab_0", Integer.valueOf(R.layout.activity_rv_tab));
            map.put("layout/activity_safe_0", Integer.valueOf(R.layout.activity_safe));
            map.put("layout/activity_sanbao_0", Integer.valueOf(R.layout.activity_sanbao));
            map.put("layout/activity_search_0", Integer.valueOf(R.layout.activity_search));
            map.put("layout/activity_service_0", Integer.valueOf(R.layout.activity_service));
            map.put("layout/activity_service_detail_0", Integer.valueOf(R.layout.activity_service_detail));
            map.put("layout/activity_setting_0", Integer.valueOf(R.layout.activity_setting));
            map.put("layout/activity_splash_0", Integer.valueOf(R.layout.activity_splash));
            map.put("layout/activity_subscribe_0", Integer.valueOf(R.layout.activity_subscribe));
            map.put("layout/activity_task_hall_0", Integer.valueOf(R.layout.activity_task_hall));
            map.put("layout/activity_topic_detail_0", Integer.valueOf(R.layout.activity_topic_detail));
            map.put("layout/activity_trumpet_0", Integer.valueOf(R.layout.activity_trumpet));
            map.put("layout/activity_user_0", Integer.valueOf(R.layout.activity_user));
            map.put("layout/activity_video_0", Integer.valueOf(R.layout.activity_video));
            map.put("layout/activity_vip_0", Integer.valueOf(R.layout.activity_vip));
            map.put("layout/activity_vip_coupon_0", Integer.valueOf(R.layout.activity_vip_coupon));
            map.put("layout/activity_web_0", Integer.valueOf(R.layout.activity_web));
            map.put("layout/activity_web2_0", Integer.valueOf(R.layout.activity_web2));
            map.put("layout/activity_web_pay_0", Integer.valueOf(R.layout.activity_web_pay));
            map.put("layout/activity_welfare_event_detail_0", Integer.valueOf(R.layout.activity_welfare_event_detail));
            map.put("layout/activity_withdrew_0", Integer.valueOf(R.layout.activity_withdrew));
            map.put("layout/activity_yun_buy_0", Integer.valueOf(R.layout.activity_yun_buy));
            map.put("layout/activity_yun_play_0", Integer.valueOf(R.layout.activity_yun_play));
            map.put("layout/dialog_bbs_game_0", Integer.valueOf(R.layout.dialog_bbs_game));
            map.put("layout/dialog_bbs_reply_0", Integer.valueOf(R.layout.dialog_bbs_reply));
            map.put("layout/dialog_bbs_sign_0", Integer.valueOf(R.layout.dialog_bbs_sign));
            map.put("layout/dialog_boss_server_0", Integer.valueOf(R.layout.dialog_boss_server));
            map.put("layout/dialog_bottom_tip_0", Integer.valueOf(R.layout.dialog_bottom_tip));
            map.put("layout/dialog_bottom_tip2_0", Integer.valueOf(R.layout.dialog_bottom_tip2));
            map.put("layout/dialog_comment_0", Integer.valueOf(R.layout.dialog_comment));
            map.put("layout/dialog_confirm_0", Integer.valueOf(R.layout.dialog_confirm));
            map.put("layout/dialog_daily_coupon_receive_0", Integer.valueOf(R.layout.dialog_daily_coupon_receive));
            map.put("layout/dialog_deal_buy_notice_0", Integer.valueOf(R.layout.dialog_deal_buy_notice));
            map.put("layout/dialog_deal_detail_0", Integer.valueOf(R.layout.dialog_deal_detail));
            map.put("layout/dialog_deal_dicker_0", Integer.valueOf(R.layout.dialog_deal_dicker));
            map.put("layout/dialog_deal_filter_0", Integer.valueOf(R.layout.dialog_deal_filter));
            map.put("layout/dialog_deal_offset_0", Integer.valueOf(R.layout.dialog_deal_offset));
            map.put("layout/dialog_deal_played_0", Integer.valueOf(R.layout.dialog_deal_played));
            map.put("layout/dialog_deal_record_type_0", Integer.valueOf(R.layout.dialog_deal_record_type));
            map.put("layout/dialog_deal_sell_notice1_0", Integer.valueOf(R.layout.dialog_deal_sell_notice1));
            map.put("layout/dialog_game_update_detail_0", Integer.valueOf(R.layout.dialog_game_update_detail));
            map.put("layout/dialog_gift_code_0", Integer.valueOf(R.layout.dialog_gift_code));
            map.put("layout/dialog_gm_game_0", Integer.valueOf(R.layout.dialog_gm_game));
            map.put("layout/dialog_gm_items_0", Integer.valueOf(R.layout.dialog_gm_items));
            map.put("layout/dialog_gm_log_0", Integer.valueOf(R.layout.dialog_gm_log));
            map.put("layout/dialog_gm_roles_0", Integer.valueOf(R.layout.dialog_gm_roles));
            map.put("layout/dialog_home_0", Integer.valueOf(R.layout.dialog_home));
            map.put("layout/dialog_invite_0", Integer.valueOf(R.layout.dialog_invite));
            map.put("layout/dialog_invite_game_0", Integer.valueOf(R.layout.dialog_invite_game));
            map.put("layout/dialog_invite_record_0", Integer.valueOf(R.layout.dialog_invite_record));
            map.put("layout/dialog_item_adjust_0", Integer.valueOf(R.layout.dialog_item_adjust));
            map.put("layout/dialog_logout_0", Integer.valueOf(R.layout.dialog_logout));
            map.put("layout/dialog_lottery_result_0", Integer.valueOf(R.layout.dialog_lottery_result));
            map.put("layout/dialog_new_game_type_0", Integer.valueOf(R.layout.dialog_new_game_type));
            map.put("layout/dialog_novice_welfare_0", Integer.valueOf(R.layout.dialog_novice_welfare));
            map.put("layout/dialog_pay_0", Integer.valueOf(R.layout.dialog_pay));
            map.put("layout/dialog_pic_0", Integer.valueOf(R.layout.dialog_pic));
            map.put("layout/dialog_pin_0", Integer.valueOf(R.layout.dialog_pin));
            map.put("layout/dialog_privacy_0", Integer.valueOf(R.layout.dialog_privacy));
            map.put("layout/dialog_qiandao_task_0", Integer.valueOf(R.layout.dialog_qiandao_task));
            map.put("layout/dialog_rule_0", Integer.valueOf(R.layout.dialog_rule));
            map.put("layout/dialog_select_trumpet_0", Integer.valueOf(R.layout.dialog_select_trumpet));
            map.put("layout/dialog_share_0", Integer.valueOf(R.layout.dialog_share));
            map.put("layout/dialog_sqk_rule_0", Integer.valueOf(R.layout.dialog_sqk_rule));
            map.put("layout/dialog_tip_0", Integer.valueOf(R.layout.dialog_tip));
            map.put("layout/dialog_to_thunt_0", Integer.valueOf(R.layout.dialog_to_thunt));
            map.put("layout/dialog_topic_record_0", Integer.valueOf(R.layout.dialog_topic_record));
            map.put("layout/dialog_topic_rule_0", Integer.valueOf(R.layout.dialog_topic_rule));
            map.put("layout/dialog_unable_game_0", Integer.valueOf(R.layout.dialog_unable_game));
            map.put("layout/dialog_update_0", Integer.valueOf(R.layout.dialog_update));
            map.put("layout/dialog_vip_coupon_buy_0", Integer.valueOf(R.layout.dialog_vip_coupon_buy));
            map.put("layout/dialog_vip_right2_0", Integer.valueOf(R.layout.dialog_vip_right2));
            map.put("layout/dialog_vip_web_0", Integer.valueOf(R.layout.dialog_vip_web));
            map.put("layout/dialog_wait_0", Integer.valueOf(R.layout.dialog_wait));
            map.put("layout/dialog_welfare3_gift_0", Integer.valueOf(R.layout.dialog_welfare3_gift));
            map.put("layout/dialog_welfare3_role_0", Integer.valueOf(R.layout.dialog_welfare3_role));
            map.put("layout/dialog_withdraw_0", Integer.valueOf(R.layout.dialog_withdraw));
            map.put("layout/dialog_withdraw2_0", Integer.valueOf(R.layout.dialog_withdraw2));
            map.put("layout/dialog_yun_device_0", Integer.valueOf(R.layout.dialog_yun_device));
            map.put("layout/dialog_yun_game_0", Integer.valueOf(R.layout.dialog_yun_game));
            map.put("layout/fragment_base_0", Integer.valueOf(R.layout.fragment_base));
            map.put("layout/fragment_bbs_0", Integer.valueOf(R.layout.fragment_bbs));
            map.put("layout/fragment_bbs2_0", Integer.valueOf(R.layout.fragment_bbs2));
            map.put("layout/fragment_bbs_index_0", Integer.valueOf(R.layout.fragment_bbs_index));
            map.put("layout/fragment_cancellation1_0", Integer.valueOf(R.layout.fragment_cancellation1));
            map.put("layout/fragment_cancellation2_0", Integer.valueOf(R.layout.fragment_cancellation2));
            map.put("layout/fragment_cancellation3_0", Integer.valueOf(R.layout.fragment_cancellation3));
            map.put("layout/fragment_cancellation4_0", Integer.valueOf(R.layout.fragment_cancellation4));
            map.put("layout/fragment_deal_0", Integer.valueOf(R.layout.fragment_deal));
            map.put("layout/fragment_deal_index_0", Integer.valueOf(R.layout.fragment_deal_index));
            map.put("layout/fragment_deal_record_0", Integer.valueOf(R.layout.fragment_deal_record));
            map.put("layout/fragment_deal_sell_0", Integer.valueOf(R.layout.fragment_deal_sell));
            map.put("layout/fragment_game_0", Integer.valueOf(R.layout.fragment_game));
            map.put("layout/fragment_game_comment_0", Integer.valueOf(R.layout.fragment_game_comment));
            map.put("layout/fragment_game_intro_0", Integer.valueOf(R.layout.fragment_game_intro));
            map.put("layout/fragment_game_server_0", Integer.valueOf(R.layout.fragment_game_server));
            map.put("layout/fragment_game_tool_0", Integer.valueOf(R.layout.fragment_game_tool));
            map.put("layout/fragment_hall_0", Integer.valueOf(R.layout.fragment_hall));
            map.put("layout/fragment_hall_game_0", Integer.valueOf(R.layout.fragment_hall_game));
            map.put("layout/fragment_home_0", Integer.valueOf(R.layout.fragment_home));
            map.put("layout/fragment_home_game_0", Integer.valueOf(R.layout.fragment_home_game));
            map.put("layout/fragment_home_list2_0", Integer.valueOf(R.layout.fragment_home_list2));
            map.put("layout/fragment_home_new_0", Integer.valueOf(R.layout.fragment_home_new));
            map.put("layout/fragment_home_schedule_0", Integer.valueOf(R.layout.fragment_home_schedule));
            map.put("layout/fragment_home_schedule2_0", Integer.valueOf(R.layout.fragment_home_schedule2));
            map.put("layout/fragment_image_0", Integer.valueOf(R.layout.fragment_image));
            map.put("layout/fragment_item_trade_0", Integer.valueOf(R.layout.fragment_item_trade));
            map.put("layout/fragment_main_0", Integer.valueOf(R.layout.fragment_main));
            map.put("layout/fragment_rv_0", Integer.valueOf(R.layout.fragment_rv));
            map.put("layout/fragment_sanbao648_0", Integer.valueOf(R.layout.fragment_sanbao648));
            map.put("layout/fragment_schedule_0", Integer.valueOf(R.layout.fragment_schedule));
            map.put("layout/fragment_subscribe_0", Integer.valueOf(R.layout.fragment_subscribe));
            map.put("layout/fragment_subscribe2_0", Integer.valueOf(R.layout.fragment_subscribe2));
            map.put("layout/fragment_topic_detail_0", Integer.valueOf(R.layout.fragment_topic_detail));
            map.put("layout/fragment_user_0", Integer.valueOf(R.layout.fragment_user));
            map.put("layout/fragment_vip_right_0", Integer.valueOf(R.layout.fragment_vip_right));
            map.put("layout/fragment_web_0", Integer.valueOf(R.layout.fragment_web));
            map.put("layout/fragment_welfare3_0", Integer.valueOf(R.layout.fragment_welfare3));
            map.put("layout/fragment_welfare4_0", Integer.valueOf(R.layout.fragment_welfare4));
            map.put("layout/item_bbs_0", Integer.valueOf(R.layout.item_bbs));
            map.put("layout/item_bbs_cate_0", Integer.valueOf(R.layout.item_bbs_cate));
            map.put("layout/item_bbs_edit_content_0", Integer.valueOf(R.layout.item_bbs_edit_content));
            map.put("layout/item_bbs_edit_tag_0", Integer.valueOf(R.layout.item_bbs_edit_tag));
            map.put("layout/item_bbs_game_0", Integer.valueOf(R.layout.item_bbs_game));
            map.put("layout/item_bbs_message_index_0", Integer.valueOf(R.layout.item_bbs_message_index));
            map.put("layout/item_bbs_message_official_0", Integer.valueOf(R.layout.item_bbs_message_official));
            map.put("layout/item_bbs_message_reply_0", Integer.valueOf(R.layout.item_bbs_message_reply));
            map.put("layout/item_bbs_search_0", Integer.valueOf(R.layout.item_bbs_search));
            map.put("layout/item_bbs_sign_0", Integer.valueOf(R.layout.item_bbs_sign));
            map.put("layout/item_bbs_sign_welfare_0", Integer.valueOf(R.layout.item_bbs_sign_welfare));
            map.put("layout/item_bbs_top_0", Integer.valueOf(R.layout.item_bbs_top));
            map.put("layout/item_bill_0", Integer.valueOf(R.layout.item_bill));
            map.put("layout/item_boss_server2_0", Integer.valueOf(R.layout.item_boss_server2));
            map.put("layout/item_boss_server_game_0", Integer.valueOf(R.layout.item_boss_server_game));
            map.put("layout/item_card_price_0", Integer.valueOf(R.layout.item_card_price));
            map.put("layout/item_card_record_0", Integer.valueOf(R.layout.item_card_record));
            map.put("layout/item_card_reward_record_0", Integer.valueOf(R.layout.item_card_reward_record));
            map.put("layout/item_card_title_0", Integer.valueOf(R.layout.item_card_title));
            map.put("layout/item_championship_reward_0", Integer.valueOf(R.layout.item_championship_reward));
            map.put("layout/item_championship_task_0", Integer.valueOf(R.layout.item_championship_task));
            map.put("layout/item_comment_category_0", Integer.valueOf(R.layout.item_comment_category));
            map.put("layout/item_comment_content_0", Integer.valueOf(R.layout.item_comment_content));
            map.put("layout/item_comment_tag_0", Integer.valueOf(R.layout.item_comment_tag));
            map.put("layout/item_daily_coupon_0", Integer.valueOf(R.layout.item_daily_coupon));
            map.put("layout/item_deal_0", Integer.valueOf(R.layout.item_deal));
            map.put("layout/item_deal_dicker_0", Integer.valueOf(R.layout.item_deal_dicker));
            map.put("layout/item_deal_filter_type_0", Integer.valueOf(R.layout.item_deal_filter_type));
            map.put("layout/item_deal_fun_0", Integer.valueOf(R.layout.item_deal_fun));
            map.put("layout/item_deal_hot_game_0", Integer.valueOf(R.layout.item_deal_hot_game));
            map.put("layout/item_deal_money_record_0", Integer.valueOf(R.layout.item_deal_money_record));
            map.put("layout/item_deal_pic_0", Integer.valueOf(R.layout.item_deal_pic));
            map.put("layout/item_deal_played_0", Integer.valueOf(R.layout.item_deal_played));
            map.put("layout/item_deal_record_type_0", Integer.valueOf(R.layout.item_deal_record_type));
            map.put("layout/item_deal_role_0", Integer.valueOf(R.layout.item_deal_role));
            map.put("layout/item_deal_sell_0", Integer.valueOf(R.layout.item_deal_sell));
            map.put("layout/item_deal_sell_child_0", Integer.valueOf(R.layout.item_deal_sell_child));
            map.put("layout/item_dialog_home_0", Integer.valueOf(R.layout.item_dialog_home));
            map.put("layout/item_dialog_invite_game_0", Integer.valueOf(R.layout.item_dialog_invite_game));
            map.put("layout/item_dialog_yun_device_0", Integer.valueOf(R.layout.item_dialog_yun_device));
            map.put("layout/item_download_0", Integer.valueOf(R.layout.item_download));
            map.put("layout/item_feedback_pic_0", Integer.valueOf(R.layout.item_feedback_pic));
            map.put("layout/item_fudai_index_0", Integer.valueOf(R.layout.item_fudai_index));
            map.put("layout/item_fun_0", Integer.valueOf(R.layout.item_fun));
            map.put("layout/item_game_banner_video_0", Integer.valueOf(R.layout.item_game_banner_video));
            map.put("layout/item_game_comment_0", Integer.valueOf(R.layout.item_game_comment));
            map.put("layout/item_game_comment_sub_0", Integer.valueOf(R.layout.item_game_comment_sub));
            map.put("layout/item_game_comment_top_0", Integer.valueOf(R.layout.item_game_comment_top));
            map.put("layout/item_game_coupon_0", Integer.valueOf(R.layout.item_game_coupon));
            map.put("layout/item_game_deal_0", Integer.valueOf(R.layout.item_game_deal));
            map.put("layout/item_game_detail_banner_0", Integer.valueOf(R.layout.item_game_detail_banner));
            map.put("layout/item_game_detail_chat_0", Integer.valueOf(R.layout.item_game_detail_chat));
            map.put("layout/item_game_detail_tag_0", Integer.valueOf(R.layout.item_game_detail_tag));
            map.put("layout/item_game_event_0", Integer.valueOf(R.layout.item_game_event));
            map.put("layout/item_game_intro1_0", Integer.valueOf(R.layout.item_game_intro1));
            map.put("layout/item_game_intro2_0", Integer.valueOf(R.layout.item_game_intro2));
            map.put("layout/item_game_intro3_0", Integer.valueOf(R.layout.item_game_intro3));
            map.put("layout/item_game_intro4_0", Integer.valueOf(R.layout.item_game_intro4));
            map.put("layout/item_game_intro_pic_0", Integer.valueOf(R.layout.item_game_intro_pic));
            map.put("layout/item_game_search_0", Integer.valueOf(R.layout.item_game_search));
            map.put("layout/item_game_service_0", Integer.valueOf(R.layout.item_game_service));
            map.put("layout/item_game_tool_0", Integer.valueOf(R.layout.item_game_tool));
            map.put("layout/item_game_type_0", Integer.valueOf(R.layout.item_game_type));
            map.put("layout/item_game_type2_0", Integer.valueOf(R.layout.item_game_type2));
            map.put("layout/item_game_update_0", Integer.valueOf(R.layout.item_game_update));
            map.put("layout/item_game_update2_0", Integer.valueOf(R.layout.item_game_update2));
            map.put("layout/item_game_vip_0", Integer.valueOf(R.layout.item_game_vip));
            map.put("layout/item_gift_0", Integer.valueOf(R.layout.item_gift));
            map.put("layout/item_gm_game_0", Integer.valueOf(R.layout.item_gm_game));
            map.put("layout/item_gm_item_0", Integer.valueOf(R.layout.item_gm_item));
            map.put("layout/item_gm_log_0", Integer.valueOf(R.layout.item_gm_log));
            map.put("layout/item_gm_role_0", Integer.valueOf(R.layout.item_gm_role));
            map.put("layout/item_gm_title_0", Integer.valueOf(R.layout.item_gm_title));
            map.put("layout/item_group_game_0", Integer.valueOf(R.layout.item_group_game));
            map.put("layout/item_group_user_0", Integer.valueOf(R.layout.item_group_user));
            map.put("layout/item_hall_filter_0", Integer.valueOf(R.layout.item_hall_filter));
            map.put("layout/item_hall_game_0", Integer.valueOf(R.layout.item_hall_game));
            map.put("layout/item_hall_game_type_0", Integer.valueOf(R.layout.item_hall_game_type));
            map.put("layout/item_home_c1_0", Integer.valueOf(R.layout.item_home_c1));
            map.put("layout/item_home_c2_0", Integer.valueOf(R.layout.item_home_c2));
            map.put("layout/item_home_c3_0", Integer.valueOf(R.layout.item_home_c3));
            map.put("layout/item_home_c4_0", Integer.valueOf(R.layout.item_home_c4));
            map.put("layout/item_home_cate_0", Integer.valueOf(R.layout.item_home_cate));
            map.put("layout/item_home_game_0", Integer.valueOf(R.layout.item_home_game));
            map.put("layout/item_home_game2_0", Integer.valueOf(R.layout.item_home_game2));
            map.put("layout/item_home_game_head_0", Integer.valueOf(R.layout.item_home_game_head));
            map.put("layout/item_home_group_0", Integer.valueOf(R.layout.item_home_group));
            map.put("layout/item_home_large_0", Integer.valueOf(R.layout.item_home_large));
            map.put("layout/item_home_mini_0", Integer.valueOf(R.layout.item_home_mini));
            map.put("layout/item_home_new_0", Integer.valueOf(R.layout.item_home_new));
            map.put("layout/item_home_pic_0", Integer.valueOf(R.layout.item_home_pic));
            map.put("layout/item_home_rank_0", Integer.valueOf(R.layout.item_home_rank));
            map.put("layout/item_home_schedule_0", Integer.valueOf(R.layout.item_home_schedule));
            map.put("layout/item_home_schedule2_0", Integer.valueOf(R.layout.item_home_schedule2));
            map.put("layout/item_home_schedule_head_0", Integer.valueOf(R.layout.item_home_schedule_head));
            map.put("layout/item_home_tab_0", Integer.valueOf(R.layout.item_home_tab));
            map.put("layout/item_home_type_0", Integer.valueOf(R.layout.item_home_type));
            map.put("layout/item_home_vertical_0", Integer.valueOf(R.layout.item_home_vertical));
            map.put("layout/item_home_video1_0", Integer.valueOf(R.layout.item_home_video1));
            map.put("layout/item_home_video2_0", Integer.valueOf(R.layout.item_home_video2));
            map.put("layout/item_home_video_game_0", Integer.valueOf(R.layout.item_home_video_game));
            map.put("layout/item_invite_list_0", Integer.valueOf(R.layout.item_invite_list));
            map.put("layout/item_invite_rank_0", Integer.valueOf(R.layout.item_invite_rank));
            map.put("layout/item_invite_withdraw_record_0", Integer.valueOf(R.layout.item_invite_withdraw_record));
            map.put("layout/item_invite_withdrew_record_0", Integer.valueOf(R.layout.item_invite_withdrew_record));
            map.put("layout/item_item_sell_game_0", Integer.valueOf(R.layout.item_item_sell_game));
            map.put("layout/item_item_sell_order_0", Integer.valueOf(R.layout.item_item_sell_order));
            map.put("layout/item_item_selling_0", Integer.valueOf(R.layout.item_item_selling));
            map.put("layout/item_item_trade_0", Integer.valueOf(R.layout.item_item_trade));
            map.put("layout/item_item_trade_record_0", Integer.valueOf(R.layout.item_item_trade_record));
            map.put("layout/item_item_trade_server_0", Integer.valueOf(R.layout.item_item_trade_server));
            map.put("layout/item_jf_record_0", Integer.valueOf(R.layout.item_jf_record));
            map.put("layout/item_lottery_fun_0", Integer.valueOf(R.layout.item_lottery_fun));
            map.put("layout/item_lottery_gift_0", Integer.valueOf(R.layout.item_lottery_gift));
            map.put("layout/item_lottery_gift_result_0", Integer.valueOf(R.layout.item_lottery_gift_result));
            map.put("layout/item_lottery_record_0", Integer.valueOf(R.layout.item_lottery_record));
            map.put("layout/item_main_tab_0", Integer.valueOf(R.layout.item_main_tab));
            map.put("layout/item_message_dicker_0", Integer.valueOf(R.layout.item_message_dicker));
            map.put("layout/item_message_official_0", Integer.valueOf(R.layout.item_message_official));
            map.put("layout/item_message_user_0", Integer.valueOf(R.layout.item_message_user));
            map.put("layout/item_my_coupon_0", Integer.valueOf(R.layout.item_my_coupon));
            map.put("layout/item_my_game_0", Integer.valueOf(R.layout.item_my_game));
            map.put("layout/item_my_gift_0", Integer.valueOf(R.layout.item_my_gift));
            map.put("layout/item_my_voucher_0", Integer.valueOf(R.layout.item_my_voucher));
            map.put("layout/item_novice_game_coupon_0", Integer.valueOf(R.layout.item_novice_game_coupon));
            map.put("layout/item_novice_game_gift_0", Integer.valueOf(R.layout.item_novice_game_gift));
            map.put("layout/item_permission_list_0", Integer.valueOf(R.layout.item_permission_list));
            map.put("layout/item_pic_select_0", Integer.valueOf(R.layout.item_pic_select));
            map.put("layout/item_point_record_0", Integer.valueOf(R.layout.item_point_record));
            map.put("layout/item_promble_0", Integer.valueOf(R.layout.item_promble));
            map.put("layout/item_qiandao_0", Integer.valueOf(R.layout.item_qiandao));
            map.put("layout/item_qiandao_record_0", Integer.valueOf(R.layout.item_qiandao_record));
            map.put("layout/item_qiandao_task_0", Integer.valueOf(R.layout.item_qiandao_task));
            map.put("layout/item_qiandao_user_0", Integer.valueOf(R.layout.item_qiandao_user));
            map.put("layout/item_recycle_0", Integer.valueOf(R.layout.item_recycle));
            map.put("layout/item_recycle_account_0", Integer.valueOf(R.layout.item_recycle_account));
            map.put("layout/item_recycle_record_0", Integer.valueOf(R.layout.item_recycle_record));
            map.put("layout/item_report_pic_0", Integer.valueOf(R.layout.item_report_pic));
            map.put("layout/item_sanbao_648_0", Integer.valueOf(R.layout.item_sanbao_648));
            map.put("layout/item_sanbao_648_coupon_0", Integer.valueOf(R.layout.item_sanbao_648_coupon));
            map.put("layout/item_sanbao_648_gift_0", Integer.valueOf(R.layout.item_sanbao_648_gift));
            map.put("layout/item_sanbao_game_0", Integer.valueOf(R.layout.item_sanbao_game));
            map.put("layout/item_sanbao_game2_0", Integer.valueOf(R.layout.item_sanbao_game2));
            map.put("layout/item_sanbao_message_0", Integer.valueOf(R.layout.item_sanbao_message));
            map.put("layout/item_schedule_time_0", Integer.valueOf(R.layout.item_schedule_time));
            map.put("layout/item_schedule_type_0", Integer.valueOf(R.layout.item_schedule_type));
            map.put("layout/item_search_history_0", Integer.valueOf(R.layout.item_search_history));
            map.put("layout/item_search_hot_0", Integer.valueOf(R.layout.item_search_hot));
            map.put("layout/item_search_type_0", Integer.valueOf(R.layout.item_search_type));
            map.put("layout/item_select_trumpet_0", Integer.valueOf(R.layout.item_select_trumpet));
            map.put("layout/item_service_0", Integer.valueOf(R.layout.item_service));
            map.put("layout/item_service_problem_0", Integer.valueOf(R.layout.item_service_problem));
            map.put("layout/item_task_0", Integer.valueOf(R.layout.item_task));
            map.put("layout/item_task_daily_0", Integer.valueOf(R.layout.item_task_daily));
            map.put("layout/item_task_monthly_0", Integer.valueOf(R.layout.item_task_monthly));
            map.put("layout/item_topic_banner_0", Integer.valueOf(R.layout.item_topic_banner));
            map.put("layout/item_topic_lottery_0", Integer.valueOf(R.layout.item_topic_lottery));
            map.put("layout/item_topic_record_0", Integer.valueOf(R.layout.item_topic_record));
            map.put("layout/item_topic_task_0", Integer.valueOf(R.layout.item_topic_task));
            map.put("layout/item_trumpet1_0", Integer.valueOf(R.layout.item_trumpet1));
            map.put("layout/item_trumpet2_0", Integer.valueOf(R.layout.item_trumpet2));
            map.put("layout/item_unable_game_0", Integer.valueOf(R.layout.item_unable_game));
            map.put("layout/item_vip_coupon_0", Integer.valueOf(R.layout.item_vip_coupon));
            map.put("layout/item_vip_coupon_level_0", Integer.valueOf(R.layout.item_vip_coupon_level));
            map.put("layout/item_vip_flb_0", Integer.valueOf(R.layout.item_vip_flb));
            map.put("layout/item_vip_fun_0", Integer.valueOf(R.layout.item_vip_fun));
            map.put("layout/item_vip_gift_0", Integer.valueOf(R.layout.item_vip_gift));
            map.put("layout/item_vip_gift_small_0", Integer.valueOf(R.layout.item_vip_gift_small));
            map.put("layout/item_vip_right_0", Integer.valueOf(R.layout.item_vip_right));
            map.put("layout/item_welfare3_1_0", Integer.valueOf(R.layout.item_welfare3_1));
            map.put("layout/item_welfare3_game_0", Integer.valueOf(R.layout.item_welfare3_game));
            map.put("layout/item_welfare3_game_small_0", Integer.valueOf(R.layout.item_welfare3_game_small));
            map.put("layout/item_welfare3_gift_0", Integer.valueOf(R.layout.item_welfare3_gift));
            map.put("layout/item_welfare3_gift2_0", Integer.valueOf(R.layout.item_welfare3_gift2));
            map.put("layout/item_welfare3_role_0", Integer.valueOf(R.layout.item_welfare3_role));
            map.put("layout/item_welfare_card_0", Integer.valueOf(R.layout.item_welfare_card));
            map.put("layout/item_welfare_event_reply_0", Integer.valueOf(R.layout.item_welfare_event_reply));
            map.put("layout/item_welfare_qiandao_0", Integer.valueOf(R.layout.item_welfare_qiandao));
            map.put("layout/item_welfare_task_0", Integer.valueOf(R.layout.item_welfare_task));
            map.put("layout/item_welfare_task2_0", Integer.valueOf(R.layout.item_welfare_task2));
            map.put("layout/item_welfare_task3_0", Integer.valueOf(R.layout.item_welfare_task3));
            map.put("layout/item_withdrew_record_0", Integer.valueOf(R.layout.item_withdrew_record));
            map.put("layout/item_yun_block_0", Integer.valueOf(R.layout.item_yun_block));
            map.put("layout/item_yun_game_0", Integer.valueOf(R.layout.item_yun_game));
            map.put("layout/item_yun_pop_device_0", Integer.valueOf(R.layout.item_yun_pop_device));
            map.put("layout/item_yun_price_0", Integer.valueOf(R.layout.item_yun_price));
            map.put("layout/item_yun_tip_0", Integer.valueOf(R.layout.item_yun_tip));
            map.put("layout/layout_banner_bbs_0", Integer.valueOf(R.layout.layout_banner_bbs));
            map.put("layout/layout_banner_home_0", Integer.valueOf(R.layout.layout_banner_home));
            map.put("layout/layout_deal_all_0", Integer.valueOf(R.layout.layout_deal_all));
            map.put("layout/layout_discount_0", Integer.valueOf(R.layout.layout_discount));
            map.put("layout/layout_game_detail_tab_0", Integer.valueOf(R.layout.layout_game_detail_tab));
            map.put("layout/layout_game_icon_0", Integer.valueOf(R.layout.layout_game_icon));
            map.put("layout/layout_game_name_0", Integer.valueOf(R.layout.layout_game_name));
            map.put("layout/layout_game_tag_0", Integer.valueOf(R.layout.layout_game_tag));
            map.put("layout/layout_game_tag2_0", Integer.valueOf(R.layout.layout_game_tag2));
            map.put("layout/layout_game_tag3_0", Integer.valueOf(R.layout.layout_game_tag3));
            map.put("layout/layout_tab_text_0", Integer.valueOf(R.layout.layout_tab_text));
        }
    }
}
