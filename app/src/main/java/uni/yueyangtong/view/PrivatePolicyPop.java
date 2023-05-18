package uni.yueyangtong.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import uni.yueyangtong.MyApplication;
import uni.yueyangtong.R;
import uni.yueyangtong.page.PandoraActivity;
import uni.yueyangtong.push.PushHelper;
import uni.yueyangtong.utils.SPUtils;
import uni.yueyangtong.utils.SpanUtils;

/**
 * @author guotianhui
 */
public class PrivatePolicyPop extends PopupWindow {

    private OnConfirmPolicyClickListener mConfirmPolicyClickListener;

    public PrivatePolicyPop(Context context) {
        super(context);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);

        View policyView = View.inflate(context, R.layout.layout_private_policy, null);
        TextView tv_policy_content = policyView.findViewById(R.id.tv_policy_content);
        tv_policy_content.setText(new SpanUtils()
                .append("感谢您信任并使用“岳办岳好”！")
                .appendLine()
                .appendLine()
                .append("我们非常注重您的个人信息和隐私保护，并依据最新法律要求更新了")
                .append("《用户服务协议》")
                .setClickSpan(ContextCompat.getColor(context,R.color.color_006AFA), false, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"用户服务协议",Toast.LENGTH_SHORT).show();
                    }
                })
                .append("和")
                .append("《隐私政策》")
                .setClickSpan(ContextCompat.getColor(context,R.color.color_006AFA), false, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"隐私政策",Toast.LENGTH_SHORT).show();
                    }
                })
                .append("，特向您推送本提示。请您仔细阅读并充分理解相关条款，其中的重点条款已为您标注，方便您了解自己的权利。我们将严格按照经您同意的条款使用您的个人信息，以便为您提供更好的服务。")
                .appendLine()
                .appendLine()
                .append("如果您同意此政策，请点击")
                .append("“同意”")
                .setBold()
                .append("并开始使用我们的产品和服务。我们将尽全力保护您的个人信息及合法权益，再次感谢您的信任！")
                .append("")
                .create());
        tv_policy_content.setMovementMethod(LinkMovementMethod.getInstance());
      /*  tv_policy_content.setText("更新日期:2021年09月27日\n" +
                "生效日期:2021年09月27日\n" +
                "\n" +
                "尊敬的“岳办岳好”用户（以下简称“您”），为了便于您使用“岳办岳好”平台（以下简称“岳办岳好”），本应用所有方岳阳市行政审批服务局（以下简称“我们”）会按照《“岳办岳好”用户隐私政策》(以下简称“本政策”)的约定，收集、使用、存储和对外提供您的个人信息。\n" +
                "您的个人信息安全对您和我们都至关重要，我们致力为每位用户提供安全的互联网环境。按照法律要求，我们采取相应的安全控制保护措施，对您的个人信息和其他数据进行严格保密。为此，“岳办岳好”APP和微信小程序（简称“岳办岳好”）产品和服务提供者（或简称“我们”）制定本《隐私政策》并提醒您：\n" +
                "本政策适用于“岳办岳好”产品及服务，包括将部分功能/服务作为第三方服务集成在其他第三方智能硬件、软件或服务中。在使用“岳办岳好”产品或服务前，请您务必仔细阅读并透彻理解本文全部内容（特别是使用加粗或下划线方式进行特别标记的内容），在确认充分理解并同意后使用相关产品或服务。您开始使用“岳办岳好”产品或服务，即表示您已充分理解并同意本政策。如您对本隐私政策有任何疑问、意见或建议，请与我们联系。\n" +
                "特别提示：您通过“岳办岳好”使用第三方服务时，您向第三方提供的信息不适用本政策。\n" +
                "本隐私政策将帮助您了解以下内容：\n" +
                "我们如何收集您的信息\n" +
                "我们如何使用您的信息\n" +
                "我们如何管理您的个人信息\n" +
                "我们如何使用Cookie和同类技术\n" +
                "个人信息存储\n" +
                "个人信息共享、转让、披露\n" +
                "个人信息保护\n" +
                "未成年人信息保护\n" +
                "我们的运营组织\n" +
                "如何联系我们\n" +
                "隐私政策更新\n" +
                "\n" +
                "我们如何收集您的信息\n" +
                "一、帮助您成为“岳办岳好”用户\n" +
                "成为我们的实名用户，需要您提供您的手机号码、姓名、身份证信息、或能证明身份的其他信息、生物识别认证（静态或动态的面部特征）创建帐号和密码，您通过实名认证后将使用您的姓名、身份证号码查询相关的服务，如查询名下电子证照和卡片信息、公积金信息、养老金信息、医保金信息、行驶证信息、驾驶证信息、不动产信息等。如您仅需要使用浏览、搜索我们提供的服务，您不需要注册成为我们的用户提供以上信息。\n" +
                "特别提示：您的实名数据将同步至岳阳市政务云平台统一用户中心。\n" +
                "在您接收系统消息推送时，则需要您允许访问手机通知权限。在您查看相关机构信息时，如需要拨打电话或查看地图，则需要您允许访问手机通话权限、地理位置权限。在您使用在线业务办理时，需要输入相关信息或上传图片，则需要您允许存储权限、相机权限。\n" +
                "您可通过“个人中心-个人信息”对个人信息（如昵称、头像、身份认证）做查询、更正及删除。\n" +
                "\n" +
                "二、向您提供产品或服务\n" +
                "您向我们提供的的信息\n" +
                "在服务使用过程中，我们可能还需要您提供必要的信息才能完成服务的使用，您可以向我们提供；如您选择不提供该信息，则无法使用该服务。\n" +
                "在您使用服务过程中收集的信息\n" +
                "为向您提供更优质的服务和更好的交互，我们会收集关于您使用产品、服务以及使用方式的信息并将这些信息进行关联，这些信息包括：（1）设备信息：我们会根据您在软件安装及使用中授予的具体权限，接收并记录您所使用的设备相关信息（例如设备型号、操作系统版本、设备设置、唯一设备标识符等软硬件特征信息）、设备所在位置相关信息（例如IP地址、GPS/北斗位置信息以及能够提供相关信息的Wi-Fi接入点、蓝牙和基站等传感器信息）。（2）日志信息：当您使用我们的网站或客户端提供的产品或服务时，我们会自动收集您对我们服务的详细使用情况，作为有关网络日志保存。例如您的搜索查询内容、IP地址、浏览器的类型、电信运营商、使用的语言、访问日期和时间、您访问的网页记录、通话状态信息等。\n" +
                "请注意，单独的设备信息、日志信息等是无法识别特定自然人身份的信息。如果我们将这类非个人信息与其他信息结合用于识别特定自然人身份，或者将其与个人信息结合使用，则在结合使用期间，这类非个人信息将被视为个人信息，除取得您授权或法律法规另有规定外，我们会将该类个人信息做匿名化、去标识化处理。当您与我们联系时，我们可能会保存您的通信/通话记录和内容或您留下的联系方式等信息，以便与您联系或帮助您解决问题，或记录相关问题的处理方案及结果。\n" +
                "\n" +
                "三、在您使用服务过程中第三方SDK采集的信息\n" +
                "为优化服务体验和服务质量，“岳办岳好”中会内嵌第三方SDK。以下内容为我们向第三方收集的协议生效时第三方SDK采集项说明，内容由SDK所属第三方提供，本文仅作转述。如您选择不提供所述信息则可能无法使用对应服务。但不影响您使用我们提供的其他服务。请注意，对于第三方SDK采集的信息，由第三方承担保密责任，相关保密政策请参考第三方隐私政策。\n" +
                "友盟+SDK：我们的产品集成友盟+SDK，友盟+SDK需要收集 采集设备标识符(IMEI/Mac/android ID/IDFA/OPENUDID/GUID、SIM 卡 IMSI 信息)，用于唯一标识设备，以便向目标设备推送消息。采集地理位置甄别推送通道，提高消息推送的区域覆盖率。\n" +
                "小米推送：由小米科技有限责任公司提供技术支持，小米消息推送服务在MIUI上为系统级通道，并且全平台通用，为用户提供稳定、可靠、高效的推送服务。会采集手机状态和身份、修改或删除存储卡中的内容、访问互联网权限、查看获取网络状态、查看WLAN状态。\n" +
                "华为HMS推送：由华为技术有限公司提供技术支持，是系统级通道，为用户提供稳定、可靠、高效的推送服务。会采集设备属性信息（硬件型号、操作系统版本、设备配置、唯一设备标识符、国际移动设备身份码IMEI、网络设备硬件地址MAC、广告标识符IDFA）、网络状态。\n" +
                "VIVO推送：vivo消息推送平台SDK集成到第三方应用是为了协助实现消息及通知的推送，除为了协助完成消息推送服务所必需的服务数据以外，不会通过第三方应用收集用户的其他个人数据信息。vivo自有业务的数据收集行为，会向用户另行展示隐私政策，并按照隐私政策的约定实施信息的收集及使用行为。vivo隐私政策请参见https://www.vivo.com.cn/about-vivo/privacy-policy。\n" +
                "OPPO推送：为向您提供推送服务，发送关于软件更新或新品发布的通知（包括营销信息）、评估广告效果等目的，您同意我们暂存从开发者处获得消息内容，并收集您的设备相关信息（如IMEI号，Serial Number，IMSI，User ID，Android ID，Google Advertising ID,手机Region设置，设备型号，手机电量，手机操作系统版本及语言）、使用推送服务的应用信息（如APP包名及版本号，运行状态）、推送SDK版本号、网络相关信息（如IP或域名连接结果，当前网络类型）、消息发送结果，通知栏状态（如通知栏权限、用户点击行为），锁屏状态（如是否锁屏，是否允许锁屏通知）。\n" +
                "微信SDK：由深圳市腾讯计算机系统有限公司提供技术支持，用于第三方账户授权登录（授权头像、ID、昵称等相关信息）。\n" +
                "高德SDK：由高德地图提供技术支持，用于APP -生活–我的生活地图信息数据呈现，SDK会申请用户定位权限，根据用户得实时定位呈现生活圈内得数据信息。\n" +
                "人脸识别：由腾讯云提供技术支持，主要用于实名认证–人脸实名认证方式（面容特征），获取用户的身份信息（姓名、手机号码、身份证号码）用于与公安的人口信息进行比对核实用户的真实性。\n" +
                "电子社保卡：由人社部提供技术支持，用于提供电子社保卡服务，为对用户更换设备导致的风险进行控制，会采集用户设备型号和设备唯一id信息。\n" +
                "腾讯TBS SDK：腾讯浏览服务SDK由深圳市腾讯计算机系统有限公司（注册地为深圳市南山区粤海街道麻岭社区科技中一路腾讯大厦35层）提供服务。为了查找用户内存卡上的可用X5内核版本，以便提升用户的X5占比，会经用户授权同意后访问用户的存储权限；如果不提供该权限，可能会影响您使用X5内核的占比。为了准确控制内核版本下发和开关下发，以及基于webview标准协议，需要获取用户的手机型号，从而保证服务的准确性、有效性。为了便于用户搜索、分享已复制的内容，需要访问剪切板，读取其中包含的链接、内容，从而保证搜索、分享等功能的实现。\n" +
                "个推GTC公共库SDK：个推GTC公共库SDK由每日互动股份有限公司提供服务。个推推送提供移动智能终端消息推送技术方案，通过高效稳定推送SDK，使APP快速集成云推送功能。\n" +
                "魅族推送(Push)是魅族公司向开发者提供的消息推送服务，通过在云端与客户端之间建立一条稳定，可靠的长连接， 为开发者提供向客户端 App 实时推送消息的服务，通过推送消息，魅族推送服务能有效地帮助开发者拉动用户活跃度，改善产品体验。"+
                "\n" +
                "四、在您使用服务过程中岳办岳好采集的手机权限\n" +
                "为优化服务体验和服务质量，“岳办岳好”会获取部分手机权限。以下内容为采集项说明。\n" +
                "1.获取定位，使用时弹窗提示获取，用于地图导航、区县选择定位等功能\n" +
                "2.拨打电话，使用时弹窗提示获取，用于市长热线等功能\n" +
                "3.获取手机信息，APP首次进入时弹窗提示获取（部分品牌安装时获取），用于信息推送等功能\n" +
                "4.读取应用列表，使用时弹窗提示获取，用于微信分享、跳转微信小程序等功能\n" +
                "5.使用相机，使用时弹窗提示获取，用于扫一扫等功能\n" +
                "6.常驻在通知栏，安装时获取（部分品牌弹窗提示），用于体验优化、消息推送等功能\n" +
                "7.查看网络状态，安装时获取，用于体验优化等功能\n" +
                "8.安装应用权限，使用时弹窗提示获取，用于更新应用等功能\n" +
                "9.控制振动器，安装时获取，用于体验优化等功能\n" +
                "10.防止手机休眠，安装时获取，用于体验优化等功能\n"+
                "11.文件、相册读写，使用时弹窗提示，用于上传头像、下载文件等功能；\n"+
                "如要修改您授权的手机权限，请在手机系统“设置-应用权限”中修改。\n"+
                "我们如何使用您的信息\n" +
                "收集您的信息是为了向您提供服务及提升我们的服务质量，为了实现这一目的，我们会把您的信息用于下列用途：\n" +
                "1.向您提供您使用的“岳办岳好”产品或服务，并维护、改进、优化这些服务及服务体验；\n" +
                "2.为预防、发现、调查欺诈、侵权、危害安全、非法或违反与我们的协议、政策或规则的行为，保护您、其他用户或公众、我们的合法权益，我们可能使用或整合您的用户信息、服务使用信息、设备信息、日志信息以及我们合作伙伴取得您授权或依据法律共享的信息，来综合判断您账户及交易风险、进行身份验证、检测及防范安全事件，并依法采取必要的记录、审计、分析、处置措施；\n" +
                "3.经您许可的其他用途。\n" +
                "4.在去标志化后，用于政务服务大数据分析、公共服务大数据分析（或者类似的用语），以不断提高政务服务能力、公共服务能力。\n" +
                "我们如何管理您的个人信息\n"+
                "一、查看及修改您的个人信息\n"+
                "1．您登录“岳办岳好”后，可以在“我的-个人信息”中，查看或修改您的用户信息资料、预留身份信息。\n"+
                "2．您可以在“我的/个人中心-隐私与安全-隐私政策”中查看最新版本的“岳办岳好”用户隐私政策。\n"+
                "二、撤回您的授权\n"+
                "您可以通过删除应用中所有用户信息、卸载本应用的方式撤回全部隐私授权，我方将不会再收集、使用或对外提供与该账户相关的个人信息，但您在使用我方服务期间提供或产生的信息，我方仍需按照监管要求的时间进行匿名化保存，且在该依法保存的时间内有权机关仍有权依法查询。\n"+
                "三、注销用户管理\n"+
                "1.您可以在“我的/个人中心-隐私与安全-注销帐号”里申请注销帐号，或联系我们0730-8880113申请注销帐号。在注销帐号之后，我们将停止为您提供服务，并依据您的要求删除您的个人信息，法律法规另有规定的除外。\n"+
                "2.在访问、修改、删除、注销帐号的相关信息时，我们可能会要求您进行身份验证并审核合法性，以保障帐号安全。\n"+
                "我们如何使用Cookie和同类技术\n" +
                "为了使您获得更轻松的访问和体验，您访问使用“岳办岳好”以及提供的相关服务时，我们可能会通过小型的数据文件识别您的身份，这么做可帮您省去重复认证的步骤，同时也可以帮助您判断当前帐号的安全状况，这些数据文件包含：Cookie、Flash Cookie、LocalStorage、SessionStorage，您的浏览器或者关联引用程序提供的其他本地存储（以下简称Cookie）。请您理解，我们的某些服务只能通过使用Cookie才能实现。如您的浏览器或者浏览器附加的服务允许，您可以修改对Cookie的接受程度(有效期)或者拒绝使用“岳办岳好”的Cookie。多数浏览器工具条中的“帮助”部分会告诉您怎么防止您的浏览器接受新的Cookie，怎么让您的浏览器在您收到一条新的Cookie的时候通知您或者怎样彻底关闭Cookie。此外，您可以通过改变浏览器附加程序设置，或者通过访问提供商的网页，来关闭或者删除浏览器附加程序使用的Cookie及类似的数据。但是这一举动在某些情况下面可能会影响您安全使用“岳办岳好”以及提供的相关服务。\n" +
                "个人信息存储\n" +
                "我们将您的个人信息存储在中华人民共和国境内，服务暂未涉及信息出境情况。我们仅在本政策所述目的所必需期间和法律法规及监管规定的时限内保存您的个人信息。\n" +
                "在您终止使用我们的服务后,我们会停止对您的信息的收集和使用,法律法规或监管部门另有规定的除外。如我们停止运营,我们将及时停止收集您个人信息的活动,将停止运营的通知以逐一送达或公告的形式通知您,并将依法依规对所持有的您的个人信息进行删除或匿名化处理。\n" +
                "\n" +
                "个人信息共享、转让、披露\n" +
                "一、共享\n" +
                "除岳阳市政务云统一用户中心，我们不会与其他组织和个人共享您的用户信息，但以下情况除外。\n" +
                "在获取明确同意的情况下共享，获得您的明确同意后，我们会与其他方共享您的用户信息；\n" +
                "在法定情形下的共享：我们可能会根据法律法规规定、诉讼、仲裁解决需要，或按行政、司法机关依法提出的要求，对外共享您的用户信息；\n" +
                "为了更好的服务您，我们可能会向长沙市城市大脑统一用户中心的指定合作平台，共享您的用户信息；\n" +
                "为了促成办理服务或协助解决争议，某些情况下只有共享您的用户信息，才能促成办理或处理您与他人的纠纷或争议。\n" +
                "\n" +
                "二、转让\n" +
                "未经您同意，我们不会将您的用户信息转让给任何公司、组织和个人。但在获得您的明确同意后，我们会向其他方转让您的用户信息。\n" +
                "披露\n" +
                "未经您同意，我们不会与任何无关第三方披露您的信息。我们可能将您的信息与我们的关联公司、第三方服务提供商、承包商及代理分享，仅用作下列用途：\n" +
                "提供“信息收集范围”“信息使用”中的相应功能或服务所必需。\n" +
                "履行我们在服务协议或本隐私政策中的义务和行使我们的权利。\n" +
                "如我们与任何上述第三方分享您的信息，我们将努力确保第三方在使用您的信息时遵守本声明及我们要求其遵守的其他适当的保密和安全措施。\n" +
                "随着我们业务的持续发展，我们以及我们的关联公司有可能进行合并、收购、资产转让或类似的交易，您的信息有可能作为此类交易的一部分而被转移。我们将遵守相关法律法规的要求，在转移前通知（包括逐一送达或公告方式）您，确保信息在转移时的机密性，以及变更后继续履行相应责任和义务。\n" +
                "共享、转让、公开披露个人信息的意外\n" +
                "遵守适用的法律法规等有关规定。\n" +
                "遵守法院判决、裁定或其他法律程序的规定。\n" +
                "遵守相关政府机关或其他有权机关的要求。\n" +
                "我们有理由确信需遵守法律法规等有关规定。\n" +
                "为执行相关服务协议或本隐私政策、维护社会公共利益、处理投诉/纠纷，保护我们的客户、我们的关联公司、其他用户或雇员的人身及财产安全或合法权益所合理必需的用途，经过您合法授权的情形。如我们因上述原因而披露您的信息，我们将在遵守法律法规相关规定及本政策的基础上及时告知您。\n" +
                "个人信息保护\n" +
                "去标识化处理\n" +
                "为了保障您的信息安全,我们在收集您的信息后,将采取各种合理必要的措施保护您的信息。例如,在技术开发环境当中,我们仅使用经过去标识化处理的信息进行统计分析；对外提供研究报告时,我们将对报告中所包含的信息进行去标识化处理。我们会将去标识化后的信息与可用于恢复识别个人的信息分开存储,确保在针对去标识化信息的后续处理中不重新识别个人。\n" +
                "安全技术及配套的管理体系\n" +
                "为保障您的信息安全,我们致力于使用各种安全技术及配套的管理体系来尽量降低您的信息被泄露、毁损、误用、非授权访问、非授权披露和更改的风险。例如:通过网络安全层软件(SSL)进行加密传输、信息加密存储、严格限制数据中心的访问。传输和存储个人敏感信息(含个人生物识别信息)时,我们将采用加密、权限控制、去标识化等安全措施。\n" +
                "设立专职部门并制定应急预案\n" +
                "我们设立了个人信息保护责任部门,针对个人信息收集、使用、共享、委托处理等开展个人信息安全影响评估。同时,我们建立了相关内控制度,对可能接触到您的信息的工作人员采取最小够用授权原则；对工作人员处理您信息的行为进行系统监控,不断对工作人员培训相关法律法规及隐私安全准则和安全意识强化宣导,并每年组织全体工作人员参加安全考试。另外,我们的相应网络系统通过了国家网络安全等级保护的测评。我们每年还会聘请外部独立第三方对我们的信息安全管理体系进行评估。\n" +
                "我们已制定个人信息安全事件应急预案,定期组织内部相关人员进行应急响应培训和应急演练,使其掌握岗位职责和应急处置策略和规程。在不幸发生个人信息安全事件后,我们将按照法律法规的要求,及时向您告知:安全事件的基本情况和可能的影响、我们已采取或将要采取的处置措施、您可自主防范和降低风险的建议、对您的补救措施等。我们将及时将事件相关情况以APP推送通知、发送邮件或短消息等方式告知您。难以逐一告知个人信息主体时,我们会采取合理、有效的方式发布公告。同时,我们还将按照监管部门要求,主动上报个人信息安全事件的处置情况，若您的合法权益受损,我们将承担相应的法律责任。\n" +
                "请您务必妥善保管好您的用户帐号及其他身份要素。您在使用我们的服务时,我们会通过您的登录名及其他身份要素来识别您的身份。一旦您泄漏了前述信息,您可能会蒙受损失,并可能对您产生不利。如您发现登录名及其他身份要素可能或已经泄露时,请您立即和我们取得联系,以便我们及时采取相应措施，以避免或降低相关损失。\n" +
                "未成年人信息保护\n" +
                "我们的产品或服务主要面向成年人，没有父母或监护人的同意，未成年人不得创建自己的用户账户。如您是18周岁以下未成年人，请在父母或监护人的陪同下阅读本隐私政策，且确保您在父母或监护人同意后使用我们的服务。如我们发现在未事先获得可证实的父母或监护人同意的情况下已收集未成年人的个人信息，我们将设法尽快删除相关数据。\n" +
                "我们的运营组织\n" +
                "单位名称：岳阳市行政审批服务局\n" +
                "办公地址：岳阳市东茅岭路347号\n" +
                "法定代表人：周鹏\n" +
                "联系方式：0730-8880113\n" +
                "如何联系我们\n" +
                "您可以拨打0730-8880113联系我们的客服，给我们提出意见、建议等。对于您提出的反馈需要我们处理的，我们将在15个工作日内给您反馈。\n" +
                "开发者名称\n" +
                "开发者名称：岳阳市行政审批服务局\n" +
                "应用名称：岳办岳好\n" +
                "隐私政策更新\n" +
                "我们可能适时修订本隐私政策的条款，未经您的明确同意，我们不会限制您按照本隐私政策所享有的权利。当隐私政策发生重大变更时，我们将以邮件、消息、公告的方式及时告知您。");*/
        policyView.findViewById(R.id.tv_cancle_policy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mConfirmPolicyClickListener != null){
                    mConfirmPolicyClickListener.onCanclePolicyClick();
                }
            }
        });
        policyView.findViewById(R.id.tv_confirm_agress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.getInstance().put(MyApplication.KEY_IS_AGREED_PRIVATE_POLICY, true);
                PushHelper.init(context);
                if(mConfirmPolicyClickListener != null){
                    mConfirmPolicyClickListener.onConfirmPolicyClick();
                }
            }
        });
        setContentView(policyView);
    }
    public void setOnConfirmPolicyClickListener(OnConfirmPolicyClickListener policyClickListener){
        mConfirmPolicyClickListener = policyClickListener;
    }
    public interface OnConfirmPolicyClickListener{
        void onCanclePolicyClick();
        void onConfirmPolicyClick();
    }
}
